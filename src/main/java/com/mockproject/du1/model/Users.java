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

	/*
	 * status
	 */
	private int status;
	
	/*
	 * Constructor for testing
	 */
	public Users() {
		
	}
	
	public Users(String firstName, String lastName, String email, String username, String password, String dob,
			String startDate, String endDate, int tenure, int status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tenure = tenure;
		this.status = status;
	}

	public Users(int userId, String firstName, String lastName, String email, String username, String password,
			String dob, String startDate, String endDate, int tenure, int status) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tenure = tenure;
		this.status = status;
	}
	
}
