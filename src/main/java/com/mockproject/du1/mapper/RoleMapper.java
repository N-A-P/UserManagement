package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Role;

public interface RoleMapper {

	/**
	 * 
	 */
	List<Role> sqlGetAllRoleSelect();

	/**
	 * 
	 */
	Role sqlGetRoleByIdSelect(@Param("roleId") int roleId);

	/**
	 * 
	 */
	List<Role> sqlGetRoleByNameOrCodeSelect(@Param("extractCondition") String extractCondition);
	
	/**
	 * 
	 */
	long sqlCountRoleByNameSelect(@Param("roleName") String roleName);
	
	/**
	 * 
	 */
	long sqlCountRoleByCodeSelect(@Param("roleCode") String roleCode);

	/**
	 * 
	 */
	int sqlEditRoleInfoUpdate(Role role);

	/**
	 * 
	 */
	int sqlRoleInsert(Role role);

}