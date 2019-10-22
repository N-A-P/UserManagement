package com.mockproject.du1.model;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder
public class MailOfUser {
	/**
	 * List User
	 */
    List<Customer> customers;

    int sendEmailUserId;

    int campaignId;

}
