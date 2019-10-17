package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class Role {
	/**
	 * Role Id
	 */
	private int roleId;
	/**
	 * Role Name
	 */
	private String roleName;
	/**
	 * Role Code
	 */
	private String roleCode;
	/**
	 * Role Id
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
