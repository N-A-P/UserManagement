package com.mockproject.du1.model;


@lombok.Data
public class RoleForPages {
	/**
	 * Selected Flag
	 */
	private int selectedFlg;
	/**
	 * Role Pages Id
	 */
	private long rolePagesId;
	/**
	 * Role Id
	 */
	private long roleId;
	/**
	 * Role Name
	 */
	private String roleName;
	/**
	 * Pages Id
	 */
	private long pagesId;
	/**
	 * Pages Name
	 */
	private String pagesName;
	/**
	 * Pages Code
	 */
	private String pagesCode;
	/**
	 * Pages Url
	 */
	private String pagesUrl;
}
