package com.mockproject.du1.model;


/*
 * UsersFull Model
 */

import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.List;

@lombok.Data
public class UsersFull {
	/**
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
	 * Seniority (Years)
	 */
	private int seniority;

	/**
	 * activated status
	 */
	private int isActivated;

	/**
	 *
	 */
	private String updatedBy;
	
	/**
	 *
	 */
    private String createTimestamp;
    /**
     *
     */
    private String updateTimestamp;
	/**
	 *
	 */
	/**
	 * String buffer of all departments
	 */
	private String departmentCodeAll;
	/**
	 * list department of Employee
	 */
	private List<Department> listDepartment;

	/**
	 * list role of Employee
	 */
	private List<Role> listRole;


}
