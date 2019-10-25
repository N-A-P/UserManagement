package com.mockproject.du1.common;

public class ValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	public ValidateException(String message) {
		super();
		this.message = message;
	}
	

}
