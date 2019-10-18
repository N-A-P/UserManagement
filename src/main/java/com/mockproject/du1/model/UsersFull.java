package com.mockproject.du1.model;


/*
 * UsersFull Model
 */

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
public class UsersFull {
	/*
	 * User Id
	 */
	private int userId;
	/*
	 * First Name
	 */
	private String firstName;
	/*
	 * Last Name
	 */
	private String lastName;
	/*
	 * Email
	 */
	private String email;
	/*
	 * Username
	 */
	private String username;
	/*
	 * Password
	 */
	private String password;
	/*
	 * Date Of birth
	 */
	private String dob;
	/*
	 * Start Date
	 */
	private String startDate;
	/*
	 * End Date
	 */
	private String endDate;
	/*
	 * Tenure(Years)
	 */
	private int tenure;

	/*
	 * status
	 */
	private int status;
	
	/*
	 * role_detail id
	 */
	private int roleDetailId;

	/*
	 * role id
	 */
	private int roleId;
	
	/*
	 * department id
	 */
	private int departmentId;

	private String updateBy;
	private LocalDateTime createTimestapm;
	private LocalDateTime updateTimestapm;



}
