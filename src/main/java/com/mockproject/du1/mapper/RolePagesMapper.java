package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.RolePages;

public interface RolePagesMapper {
	/**
	 * 
	 */
	List<RolePages> sqlGetRolePagesByRoleIdSelect(int roleId);

	/**
	 * 
	 */
	long sqlCheckRolePagesExistSelect(@Param("roleId") int roleId, @Param("pageId") int pageId);

	/**
	 * 
	 */
	int sqlRolePagesDelete(@Param("roleId") int roleId, @Param("pageId") int pageId);

	/**
	 * 
	 */
	int sqlRolePagesInsert(RolePages rolePages);
}
