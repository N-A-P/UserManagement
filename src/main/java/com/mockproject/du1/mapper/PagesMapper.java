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
	Pages sqlGetPagesByIdSelect(@Param("pagesId") int pagesId);

	/**
	 * 
	 */
	List<Pages> sqlGetPagesByNameOrCodeSelect(@Param("extractCondition") String extractCondition);
	
	/**
	 * 
	 */
	long sqlCountPagesByNameSelect(@Param("pagesName") String pagesName);
	
	/**
	 * 
	 */
	long sqlCountPagesByCodeSelect(@Param("pagesCode") String pagesCode);

	/**
	 * 
	 */
	int sqlEditPagesInfoUpdate(Pages page);

	/**
	 * 
	 */
	int sqlPagesInsert(Pages page);
}
