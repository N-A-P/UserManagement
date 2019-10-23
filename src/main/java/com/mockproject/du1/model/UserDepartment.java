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

	private String updateBy;
	private String createTimestamp;
	private String updateTimestamp;


}
