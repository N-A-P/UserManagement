package com.mockproject.du1.common;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;

	public CustomException(String message) {
		super();
		this.message = message;
	}
	
}
