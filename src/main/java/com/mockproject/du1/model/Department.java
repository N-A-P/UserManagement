package com.mockproject.du1.model;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

/*
 * Department Model
 */
@lombok.Data
public class Department {
	/**
	 * Department Id
	 */
	private int departmentId;
	/**
	 * Department Name
	 */
	private String departmentName;
	/**
	 * Department Code
	 */
	private String departmentCode;
	/**
	 * Number Of Employees
	 */
	private int numberOfEmployees;
	/**
	 * isActivated
	 */
	private int isActivated;
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
