package com.mockproject.du1.model;

/*
 * Users Model
 */
@lombok.Data
public class Users {
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
}
