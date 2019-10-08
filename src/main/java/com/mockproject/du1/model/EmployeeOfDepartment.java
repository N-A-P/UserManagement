package com.mockproject.du1.model;

@lombok.Data
public class EmployeeOfDepartment {
	/**
	 * User Id
	 */
	private int userId;
	/**
	 * User First Name
	 */
	private String firstName;
	/**
	 * User Last Name
	 */
	private String lastName; 
	/**
	 * User Email
	 */
	private String email;
	/**
	 * User Tenure
	 */
	private int tenure;
	/**
	 * Role Id
	 */
	private int roleId;
	/**
	 * Role Name
	 */
	private String roleName;
	/**
	 * Department Id
	 */
	private int departmentId;
	/**
	 * Department Name
	 */
	private String departmentName;

}
