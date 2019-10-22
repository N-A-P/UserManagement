package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Customer {
	/**
	 * Customer Id
	 */
    private int customerId;
    /**
	 * Customer Name
	 */
    private String customerName;
    /**
	 * Customer Email
	 */
    private String firstName;
    private String lastName;
    private String customerEmail;
    /**
	 * Customer isActivated
	 */
    private String dob;
    private int isActivated;
    /**
	 * Customer address
	 */
    private String address;
    /**
	 * Customer company
	 */
    private String company;
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
