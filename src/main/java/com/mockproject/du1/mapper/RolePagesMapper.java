package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.RolePages;
import com.mockproject.du1.model.RolePagesExample;

public interface RolePagesMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	long countByExample(RolePagesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByExample(RolePagesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insert(RolePages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insertSelective(RolePages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	List<RolePages> selectByExample(RolePagesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	RolePages selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExampleSelective(@Param("record") RolePages record, @Param("example") RolePagesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExample(@Param("record") RolePages record, @Param("example") RolePagesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKeySelective(RolePages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.role_pages
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKey(RolePages record);

	/**
	 * 
	 */
	List<RolePages> sqlGetRolePagesByRoleIdSelect(int roleId);

	/**
	 * 
	 */
	long sqlCheckRolePagesExistSelect(@Param("roleId") long roleId, @Param("pageId") long pageId);

	/**
	 * 
	 */
	int sqlRolePagesDelete(@Param("roleId") long roleId, @Param("pageId") long pageId);

	/**
	 * 
	 */
	int sqlRolePagesInsert(RolePages rolePages);
}
