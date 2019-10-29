package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
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
	private String updateBy;
	private String createTimestamp;
	private String updateTimestamp;


}
