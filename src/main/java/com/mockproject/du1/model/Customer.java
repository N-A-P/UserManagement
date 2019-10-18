package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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
	 * UpdateInfo
	 */
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;


}
