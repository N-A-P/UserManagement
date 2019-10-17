package com.mockproject.du1.model;

import javax.persistence.Embedded;

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
	 * Status
	 */
	private int isActivated;
	/**
	 * Update Info
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
