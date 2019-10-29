package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

public interface UsersMapper {

	/**
	 * Get User Login
	 *
	 * @param users
	 * @return Number Of User
	 */
	long sqlCheckLoginSelect(@Param("username") String username, @Param("password") String password);

	/**
	 * Get All UserFull
	 *
	 */
	List<UsersFull> sqlGetAllUserFullSelect();
	
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
	 * Get User By Id
	 *
	 * @return User
	 */
	Users sqlGetUserByIdSelect(@Param("userId") long userId);

	
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
	
	/**
	 * Update Role_Detail table info
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlUpdateRoleDetailUpdate(Integer userId,Integer roleId);
	
	/**
	 * Add record to department_detail
	 *
	 * @return 0 or 1 if Insert query success or not
	 */
	int sqlInsertDepartmentDetailInsert(Integer status, Integer departmentId, Integer userId);

	/**
	 * Update record in department_detail
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlUpdateDepartmentDetailUpdate(Integer userId,Integer userStatus);
	
	/**
	 * Select record in department_detail
	 *
	 * @return List of record
	 */
	int sqlSelectDepartmentDetailSelect(Integer userId,Integer departmentId);

	/**
	 * Delete record in department_detail
	 *
	 * @return 0 or 1 if Delete query success or not
	 */
	int sqlDeleteDepartmentDetailDelete(Integer userId);

	/**
	 * Update users.status=0 
	 *
	 * @return 0 or 1 if Delete query success or not
	 */
	int sqlDeleteUserUpdate(Integer userId);


}