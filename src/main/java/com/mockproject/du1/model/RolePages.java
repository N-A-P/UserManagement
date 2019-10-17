package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class RolePages {
	/**
	 * Role Pages Id
	 */
	private int rolePagesId;
	/**
	 * Role Id
	 */
	private int roleId;
	/**
	 * Pages Id
	 */
	private int pagesId;
	/**
	 * Update Info
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
