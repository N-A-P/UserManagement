package com.mockproject.du1.model;

import lombok.Builder;

import java.util.Date;

@lombok.Data
@Builder
public class CampaignCustomer {
	/**
	 * Campaign Customer Id
	 */
	private int campaignCustomerId;
	/**
	 * Campaign send Time
	 */
	private Date sendTime;
	/**
	 * Campaign sendBy
	 */
	private int sendBy;
	/**
	 * Campaign joinDate
	 */
	private Date joinDate;
	/**
	 * Campaign Id
	 */
	private int campaignId;
	/**
	 * Customer Id
	 */
	private int customerId;
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
