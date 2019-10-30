package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.UserDepartment;
import com.mockproject.du1.model.UserRole;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;

/**
 * @author nnhlam & huong
 *
 */

public interface UsersMapper {

	/**
	 * Get User Login
	 *
	 * @param users
	 * @return Number Of User
	 */
	Users sqlGetUserLoginSelect(@Param("username") String username);

	/**
	 * Get All UserFull
	 *
	 */
	List<UsersFull> sqlGetAllUserFullSelect(@Param("isActivated") Integer isActivated);

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
	 * Update record in department_detail
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlUpdateUserDepartmentUpdate(Integer userId, Integer userStatus);

	/**
	 * Select record in user_department
	 *
	 * @return List of user_department_id
	 */
	List<Integer> sqlGetUserDepartmentSelect(Integer userId, Integer departmentId);

	/**
	 * Select record in user_role
	 *
	 * @return List of user_role_id
	 */
	List<Integer> sqlGetUserRoleSelect(Integer userId, Integer departmentId);

	/**
	 * Select count user_id in user_department
	 *
	 * @return number of user_id
	 */
	int sqlCountUserDepartmentSelect(Integer userId);

	/**
	 * Select count user_id in user_role
	 *
	 * @return number of user_id
	 */
	int sqlCountUserRoleSelect(Integer userId);

	/**
	 * Update users
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlActivateDeactivateUserUpdate(Users user);

	/**
	 * Update user_department
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlActivateDeactivateUserDepartmentUpdate(UserDepartment userDepartment);

	/**
	 * Update user_role
	 *
	 * @return 0 or 1 if Update query success or not
	 */
	int sqlActivateDeactivateUserRoleUpdate(UserRole userRole);

	/**
	 * @param insert User
	 * @return 0 or 1
	 */
	int sqlCreateUserInsert(Users user);

	/**
	 * @param update User by id
	 * @return 0 or 1
	 */
	int sqlUpdateUserUpdate(Users user);

}