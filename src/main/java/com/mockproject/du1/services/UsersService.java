package com.mockproject.du1.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.mockproject.du1.common.EmailValidate;
import com.mockproject.du1.exception.CustomException;
import com.mockproject.du1.mapper.RoleMapper;
import com.mockproject.du1.mapper.UsersMapper;
import com.mockproject.du1.model.UserDepartment;
import com.mockproject.du1.model.UserRole;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

@Service
public class UsersService {

	private String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private RoleMapper roleMapper;

	HttpServletRequest request = null;

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

	
	/* ---------------- GET ALL USERFULL LIST ------------------------ */
	public List<UsersFull> getAllUserFull(int isActivated) {
		return usersMapper.sqlGetAllUserFullSelect(isActivated);
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
	 * ---------------- SEARCH ALL USER ------------------------
	 */
	public List<Users> getSearchResult(String toSearch) {
		return usersMapper.sqlSelectSearchSelect(toSearch);
	}

	/*
	 * ---------------- ACTIVATE-DEACTIVATE USER ------------------------
	 */
	public int activateDeactivate(Users user) {
		if (user.getIsActivated() == 1)
			return deactivateUser(user);
		if (user.getIsActivated() == 0)
			return activateUser(user);
		return -1;
	}

	/*
	 * ---------------- ACTIVATE USER ------------------------
	 */
	private int activateUser(Users user) {
		UserRole updatedUserRole = new UserRole();
		// setters

		UserDepartment userDepartment = new UserDepartment();
		// setters
		return 0;
	}

	/*
	 * ---------------- DEACTIVATE USER ------------------------
	 */
	private int deactivateUser(Users user) {
		UserRole updatedUserRole = new UserRole();
		// setters

		UserDepartment userDepartment = new UserDepartment();
		// setters
		return 0;

	}

	public boolean registerNewCustomer(Users user) throws CustomException {
		if (EmailValidate.isEmail(user.getEmail())) {
			try {
				user.setRegisteredDate(java.time.LocalDate.now().toString());
				user.setSeniority(0);
				user.setIsActivated(0);
				user.setCreateTimestamp(currentTimestamp);
				usersMapper.sqlCreateUserInsert(user);
				return true;
			} catch (DuplicateKeyException e) {
				throw new CustomException("Email or Username existed!");
			}
		} else {
			throw new CustomException("Email format incorrect");
		}
	}



	public boolean updateUserInfo(Users user) throws CustomException {
		if (EmailValidate.isEmail(user.getEmail())) {
			try {
				user.setUpdateTimestamp(currentTimestamp);
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
