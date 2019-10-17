package com.mockproject.du1.model;

/*
 * Department Detail Model
 */
@lombok.Data
public class UserDepartment {
	/**
	 * Department Detail Id
	 */
	private int userDepartmentId;
	/**
	 * Department Id
	 */
	private int departmentId;
	/**
	 * User Id
	 */
	private int userId;
	/**
	 * Number Of Employees
	 */
	private int numberOfEmployees;
	/**
	 * Status
	 */
	private int stayOfLeave;

	private String joinDate;

	private String leaveDate;

	private UpdateInfo updateInfo;

}