package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Campaign {
	/**
	 * Campaign Id
	 */
	private int campaignId;
	/**
	 * Title
	 */
	private String title;
	/**
	 * Campaign description
	 */
	private String description;
	/**
	 * Campaign duration
	 */
	private int duration;
	/**
	 * Campaign start Date
	 */
	private String startDate;
	/**
	 * Campaign end Date
	 */
	private String endDate;
	/**
	 * Campaign email Template Id
	 */
	private int emailTemplateId;
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
