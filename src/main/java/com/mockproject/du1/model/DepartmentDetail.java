package com.mockproject.du1.model;

/*
 * Department Detail Model
 */
@lombok.Data
public class DepartmentDetail {
	/**
	 * Department Detail Id
	 */
	private int departmentDetailId;
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
	private int status;
}
