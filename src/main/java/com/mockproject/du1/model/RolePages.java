package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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
	private LocalDateTime createTimestamp;
	/**
	 * Update Timestamp
	 */
	private LocalDateTime updateTimestamp;

}
