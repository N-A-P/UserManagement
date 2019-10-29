package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

public interface UsersMapper {

	/**
	 * Get User By Username
	 *
	 * @return User
	 */
	Users sqlGetUserByUsernameSelect(@Param("username") String username);

	
	
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
	 * Get All User by Search id
	 *
	 * @return 0 or 1 if Select query success or not
	 */
	List<Users> sqlSelectSearchSelect(String toSearch);

	/**
	 * Update users, user_role, user_department
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlActivateUserUpdate(int userId);

}