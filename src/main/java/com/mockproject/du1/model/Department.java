package com.mockproject.du1.model;

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
	private String createTimestamp;
	/**
	 * Update Timestamp
	 */
	private String updateTimestamp;

}
