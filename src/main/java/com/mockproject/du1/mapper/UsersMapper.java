package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Users;

public interface UsersMapper {

	/**
	 * Get User Login
	 *
	 * @param users
	 * @return Number Of User
	 */
	long sqlCheckLoginSelect(@Param("username") String username, @Param("password") String password);

	/**
	 * Get All User
	 *
	 * @return Users
	 */
	List<Users> sqlGetAllUserSelect();

	/**
	 * Get User By Username
	 *
	 * @return User
	 */
	Users sqlGetUserByUsernameSelect(@Param("username") String username);

	/**
	 * Get User By Email
	 *
	 * @return User
	 */
	Users sqlGetUserByEmailSelect(@Param("email") String email);

	/**
	 * Add User
	 *
	 * @return 0 or 1 if Insert query success or not
	 */
	int sqlCreateUserInsert(Users user);
	
	/**
	 * Update User table info
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlUpdateUserUpdate(Users user);
	
	
	
	


}