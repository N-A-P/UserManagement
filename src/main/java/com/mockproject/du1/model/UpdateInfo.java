package com.mockproject.du1.model;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class UpdateInfo implements Serializable {
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
