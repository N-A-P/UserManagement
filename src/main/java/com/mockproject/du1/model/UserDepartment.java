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
	 * Stay Or Leave
	 */
	private int stayOrLeave;
	/**
	 * Join Date
	 */
	private String joinDate;
	/**
	 * Leave Date
	 */
	private String leaveDate;
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
