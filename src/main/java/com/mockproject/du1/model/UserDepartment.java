package com.mockproject.du1.model;

import javax.persistence.Embedded;

/*
 * User Department Model
 */
@lombok.Data
public class UserDepartment {
	/**
	 * User Department Id
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
	 * Update Info
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
