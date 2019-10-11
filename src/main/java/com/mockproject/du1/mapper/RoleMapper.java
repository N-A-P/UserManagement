package com.mockproject.du1.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

	/**
	 * Add Role_detail
	 *
	 * @return Insert query successful or not
	 */
	int sqlCreateRoleDetailInsert(@Param("roleId")int roleId,@Param("userId")int userId);
	
}