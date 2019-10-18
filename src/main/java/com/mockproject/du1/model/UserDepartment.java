package com.mockproject.du1.model;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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
	private String updateBy;
	private LocalDateTime createTimestapm;
	private LocalDateTime updateTimestapm;

}
