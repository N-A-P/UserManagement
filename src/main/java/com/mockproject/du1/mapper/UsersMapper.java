package com.mockproject.du1.mapper;

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
	Users sqlGetAllUserSelect();
	
	/**
	 * Get User By Username
	 *
	 * @return User
	 */
	Users sqlGetUserByUsernameSelect(String username);

}