package com.mockproject.du1.model;

import lombok.Builder;

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
	 * Update By
	 */
	private String updateBy;
	/**
	 * Create Timestamp
	 */
	private String createTimestamp;
	/**
	 * Update Timestamp
	 */
	private String updateTimestamp;

}
