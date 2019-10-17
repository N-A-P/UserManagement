package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
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
	 * Update Info
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
