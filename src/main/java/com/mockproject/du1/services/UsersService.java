package com.mockproject.du1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.mockproject.du1.mapper.RoleMapper;
import com.mockproject.du1.mapper.UsersMapper;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

@Service
public class UsersService {

	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private RoleMapper roleMapper;

	/* ---------------- GET ALL USER LIST ------------------------ */
	public List<Users> getAllUser() {
		return usersMapper.sqlGetAllUserSelect();
	}

	/* ---------------- GET ALL USERFULL LIST ------------------------ */
	public List<UsersFull> getAllUserFull() {
		return usersMapper.sqlGetAllUserFullSelect();
	}

	/* ---------------- GET 1 USER BY USERNAME ------------------------ */
	public Users getUserByUsername(String username) {
		return usersMapper.sqlGetUserByUsernameSelect(username);
	}

	/* ---------------- GET 1 USER BY EMAIL ------------------------ */
	public Users getUserByEmail(String email) {
		return usersMapper.sqlGetUserByEmailSelect(email);
	}

	/* ---------------- GET 1 USER BY ID ------------------------ */
	public Users getUserById(long userId) {
		return usersMapper.sqlGetUserByIdSelect(userId);
	}

	/* ---------------- GET USER LIST BY EMAIL LIST ------------------------ */
	public List<Users> getUsersListByEmails(List<String> emails) {
		List<Users> userList = new ArrayList<>();
		for (String email : emails) {
			userList.add(getUserByEmail(email));
		}
		return userList;
	}

	/* ---------------- GET ROLE LIST OF 1 USER ------------------------ */
	public List<GrantedAuthority> getAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

	/*
	 * ---------------- CHECK LOGIN USERNAME AND PASSWORD MATCH DB
	 * ------------------------
	 */
	public boolean checkLogin(Users user) {
		try {
			if (usersMapper.sqlCheckLoginSelect(user.getUsername(), user.getPassword()) == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * ---------------- ADD NEW USER TO TABLE USER, ROLE_DETAIL
	 * ------------------------
	 */
	public boolean registerNewCustomer(Users user) {
		if (((getUserByUsername(user.getUsername())) == null) && ((getUserByEmail(user.getEmail())) == null))
			if (usersMapper.sqlCreateUserInsert(user) != 0) {
				roleMapper.sqlCreateRoleDetailInsert(1,
						usersMapper.sqlGetUserByUsernameSelect(user.getUsername()).getUserId());
				return true;
			}
		return false;
	}

	/*
	 * ---------------- UPDATE USER IN TABLE USER, ROLE_DETAIL,
	 * ------------------------
	 */
	public boolean updateUserInfo(UsersFull userFull) {
		// check if new email existed
		Users tempUser = getUserByEmail(userFull.getEmail());
		if ((tempUser == null) || (tempUser.getUserId() == userFull.getUserId())) {
			Users newUser = new Users(userFull.getUserId(), userFull.getFirstName(), userFull.getLastName(),
					userFull.getEmail(), userFull.getUsername(), userFull.getPassword(), userFull.getDob(),
					userFull.getStartDate(), userFull.getEndDate(), userFull.getTenure(), 1);
			if (userFull.getRoleId() == 4) {
				// check if roleId=4 (customer)
				// update all userId record in Table department_detail to status=0 (deactivated)
				usersMapper.sqlUpdateDepartmentDetailUpdate(userFull.getUserId(), 0);
			} else {
				// check if roleId!=4 (employee/manager/admin)
				// update all userId record in Table department_detail to status=1 (activated)
				usersMapper.sqlUpdateDepartmentDetailUpdate(userFull.getUserId(), 1);
				// if record not existed in department_detail, add record
				if ((usersMapper.sqlSelectDepartmentDetailSelect(userFull.getUserId(),
						userFull.getDepartmentId())) == 0)
					usersMapper.sqlInsertDepartmentDetailInsert(1, userFull.getDepartmentId(), userFull.getUserId());
			}
			// update record in table user & role_detail
			usersMapper.sqlUpdateRoleDetailUpdate(userFull.getUserId(), userFull.getRoleId());
			usersMapper.sqlUpdateUserUpdate(newUser);
			return true;
		}
		return false;

//				// check if roleId=4 (customer), delete record department_detail
//				usersMapper.sqlDeleteDepartmentDetailDelete(userFull.getUserId());
//			} else {
//				// check if roleId!=4 (employee/manager/admin), add record department_detail
//				if ((usersMapper.sqlSelectDepartmentDetailSelect(userFull.getUserId(),
//						userFull.getDepartmentId())) == 0)
//					usersMapper.sqlInsertDepartmentDetailInsert(1, userFull.getDepartmentId(), userFull.getUserId());
//			}
//			// update record in table user & role_detail
//			usersMapper.sqlUpdateRoleDetailUpdate(userFull.getUserId(), userFull.getRoleId());
//			usersMapper.sqlUpdateUserUpdate(newUser);
//			return true;
//		}
//		return false;
	}

	/*
	 * ---------------- DELETE USER: SET USER.STATUS=0 && SET DEP_DETAIL.STATUS=0
	 * ------------------------
	 */
	public boolean deleteUser(UsersFull userFull) {
		usersMapper.sqlUpdateDepartmentDetailUpdate(userFull.getUserId(), 0);
		if (usersMapper.sqlDeleteUserUpdate(userFull.getUserId()) == 1)
			return true;
		return false;

	}

}
