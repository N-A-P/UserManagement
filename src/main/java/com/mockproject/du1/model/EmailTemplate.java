package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class EmailTemplate {
	/**
	 * Email Template Id
	 */
	private int emaiTemplateId;
	/**
	 * Email Template title
	 */
	private String title;
	/**
	 * Email Template body
	 */
	private String body;
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
