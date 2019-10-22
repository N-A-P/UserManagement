package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Pages;

public interface PagesMapper {

	/**
	 * 
	 */
	List<Pages> sqlGetAllPagesSelect();

	/**
	 * 
	 */
	Pages sqlGetPagesByIdSelect(@Param("pageId") int pageId);

	/**
	 * 
	 */
	List<Pages> sqlGetPagesByNameOrCodeSelect(@Param("extractCondition") String extractCondition);
	
	/**
	 * 
	 */
	long sqlCountPagesByNameSelect(@Param("pageName") String pageName);
	
	/**
	 * 
	 */
	long sqlCountPagesByCodeSelect(@Param("pageCode") String pageCode);

	/**
	 * 
	 */
	int sqlEditPagesInfoUpdate(Pages page);

	/**
	 * 
	 */
	int sqlPagesInsert(Pages page);
}
