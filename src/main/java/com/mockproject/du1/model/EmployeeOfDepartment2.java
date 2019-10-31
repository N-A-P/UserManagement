package com.mockproject.du1.model;

import java.util.List;

@lombok.Data
public class EmployeeOfDepartment2 {
	/***
	 * User Id
	 */
	private int userId;
	/**
	 * First Name
	 */
	private String firstName;
	/**
	 * Last Name
	 */
	private String lastName;
	/**
	 * Email
	 */
	private String email;
	/**
	 * Username
	 */
	private String username;
	/**
	 * Password
	 */
	private String password;
	/**
	 * Date Of birth
	 */
	private String dob;
	/**
	 * Registered Date
	 */
	private String registeredDate;
	/**
	 * Activated Date
	 */
	private String activatedDate;
	/**
	 * End Date
	 */
	private String endDate;
	/**
	 * Seniority(Years)
	 */
	private int seniority;

	/**
	 * IsActivated
	 */
	private int isActivated;

	/**
	 * 
	 */
	private List<Department> departments;

	/**
	 * 
	 */
	private List<Role> roles;

}
