package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

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
    private String customerEmail;
    /**
	 * Customer isActivated
	 */
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
	 * UpdateInfo
	 */
    @Embedded
    private UpdateInfo updateInfo;


}
