package com.mockproject.du1.mapper;

import org.apache.ibatis.annotations.Param;

public interface UsersMapper {

	/**
	 * Get User Login
	 *
	 * @param users
	 * @return Number Of User
	 */
	long sqlCheckLoginSelect(@Param("username") String username, @Param("password") String password);

}