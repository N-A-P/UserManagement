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
	 * Number Of Employees
	 */
	private int numberOfEmployees;
	/**
	 * Status
	 */
	private int isActivated;
	private UpdateInfo updateInfo;

}
