package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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
	private LocalDateTime createTimestamp;
	/**
	 * Update Timestamp
	 */
	private LocalDateTime updateTimestamp;

}
