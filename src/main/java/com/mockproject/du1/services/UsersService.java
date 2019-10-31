package com.mockproject.du1.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.mockproject.du1.common.EmailValidate;
import com.mockproject.du1.exception.CustomException;
import com.mockproject.du1.mapper.RoleMapper;
import com.mockproject.du1.mapper.UsersMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.UserDepartment;
import com.mockproject.du1.model.UserRole;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

@Service
public class UsersService {
	/**
	 *
	 */
	private String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	/**
	 *
	 */
	@Autowired
	private UsersMapper usersMapper;
	/**
	 *
	 */
	@Autowired
	private RoleMapper roleMapper;
	/**
	 *
	 */
	HttpServletRequest request = null;
	/**
	 *
	 */
	String usernameLogin;

	private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

	/*
	 * ---------------- CHECK LOGIN USERNAME AND PASSWORD MATCH DB
	 * ------------------------
	 */
	public Users getUserLogin(String username) {
		return usersMapper.sqlGetUserLoginSelect(username);
	}


	/* ---------------- GET ALL USERFULL LIST ------------------------ */
	public List<UsersFull> getAllUserFull(Integer isActivated) {
		List<UsersFull> result = usersMapper.sqlGetAllUserFullSelect(isActivated);

		// concat all department_code of each UserFull in List<UserFull>
		for (UsersFull member : result) {
			if (member.getListDepartment() != null) {
				StringBuffer sb = new StringBuffer();
				for (Department department : member.getListDepartment())
					sb.append(department.getDepartmentCode() + " ");
				member.setDepartmentCodeAll(sb.toString());
			}
		}
		return result;
	}

	/* ---------------- GET 1 USER BY USERNAME ------------------------ */
	public Users getUserByUsername(String username) {
		return usersMapper.sqlGetUserByUsernameSelect(username);
	}

	/* ---------------- GET ROLE LIST OF 1 USER ------------------------ */
	public List<GrantedAuthority> getAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

	/*
	 * ---------------- ACTIVATE-DEACTIVATE USER ------------------------
	 */
	public int activateDeactivate(Users user) {
		if (user.getIsActivated() == 1) {
			logger.info("Deactivate user id=" + user.getUserId());
			return deactivateUser(user);
		}
		if (user.getIsActivated() == 0) {
			logger.info("Activate user id=" + user.getUserId());
			return activateUser(user);
		}
		return 0;
	}

	/*
	 * ---------------- ACTIVATE USER ------------------------
	 */
	private int activateUser(Users user) {
		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// check if user was in department & role
		if (usersMapper.sqlCountUserDepartmentSelect(user.getUserId()) != 0
				&& usersMapper.sqlCountUserRoleSelect(user.getUserId()) != 0) {
			// create parameters
			user.setActivatedDate(java.time.LocalDate.now().toString());
			user.setIsActivated(1);
			user.setUpdatedTimestamp(currentTimestamp);
			user.setUpdatedBy(usernameLogin);

			UserRole updatedUserRole = new UserRole();
			updatedUserRole.setUserId(user.getUserId());
			updatedUserRole.setLeaveDate(null);
			updatedUserRole.setUpdateBy(usernameLogin);
			updatedUserRole.setUpdateTimestamp(currentTimestamp);

			UserDepartment updatedUserDepartment = new UserDepartment();
			updatedUserDepartment.setUserId(user.getUserId());
			updatedUserDepartment.setLeaveDate(null);
			updatedUserDepartment.setStayOrLeave(1);
			updatedUserDepartment.setUpdateTimestamp(currentTimestamp);
			updatedUserDepartment.setUpdateBy(usernameLogin);

			// activate all user with user_id in 3 tables
			usersMapper.sqlActivateDeactivateUserUpdate(user);
			usersMapper.sqlActivateDeactivateUserDepartmentUpdate(updatedUserDepartment);
			usersMapper.sqlActivateDeactivateUserRoleUpdate(updatedUserRole);
			return 1;
		}
		return 0;
	}

	/*
	 * ---------------- DEACTIVATE USER ------------------------
	 */
	private int deactivateUser(Users user) {
		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// create parameters
		user.setIsActivated(0);
		user.setUpdatedTimestamp(currentTimestamp);
		user.setUpdatedBy(usernameLogin);

		UserRole updatedUserRole = new UserRole();
		updatedUserRole.setUserId(user.getUserId());
		updatedUserRole.setLeaveDate(java.time.LocalDate.now().toString());
		updatedUserRole.setUpdateBy(usernameLogin);
		updatedUserRole.setUpdateTimestamp(currentTimestamp);

		UserDepartment updatedUserDepartment = new UserDepartment();
		updatedUserDepartment.setUserId(user.getUserId());
		updatedUserDepartment.setLeaveDate(java.time.LocalDate.now().toString());
		updatedUserDepartment.setStayOrLeave(0);
		updatedUserDepartment.setUpdateTimestamp(currentTimestamp);
		updatedUserDepartment.setUpdateBy(usernameLogin);

		// activate all user with user_id in 3 tables
		usersMapper.sqlActivateDeactivateUserUpdate(user);
		usersMapper.sqlActivateDeactivateUserDepartmentUpdate(updatedUserDepartment);
		usersMapper.sqlActivateDeactivateUserRoleUpdate(updatedUserRole);
		return 1;
	}

	/*
	 * ---------------- REGISTER USER ------------------------
	 */
	public boolean registerNewCustomer(Users user) throws CustomException {
		if (EmailValidate.isEmail(user.getEmail())) {
			try {
				user.setRegisteredDate(java.time.LocalDate.now().toString());
				user.setSeniority(0);
				user.setIsActivated(0);
				user.setCreatedTimestamp(currentTimestamp);
				usersMapper.sqlCreateUserInsert(user);
				return true;
			} catch (DuplicateKeyException e) {
				throw new CustomException("Email or Username existed!");
			}
		} else {
			throw new CustomException("Email format incorrect");
		}
	}

	/*
	 * ---------------- EDIT USER ------------------------
	 */
	public boolean updateUserInfo(Users user) throws CustomException {
		if (EmailValidate.isEmail(user.getEmail())) {
			try {
				user.setUpdatedTimestamp(currentTimestamp);
				usersMapper.sqlUpdateUserUpdate(user);
				return true;
			} catch (DuplicateKeyException e) {
				throw new CustomException("Email or Username existed!");
			}
		} else {
			throw new CustomException("Email format incorrect");
		}

	}

}
