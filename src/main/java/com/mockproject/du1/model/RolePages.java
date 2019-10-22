package com.mockproject.du1.model;

import lombok.Builder;

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
