package com.mockproject.du1.common;

public class EmailValidate {

	private static final String pattern = "(^[a-z0-9_]{3,16})@(([a-z]+\\.)([a-z]+))+$";

	public static boolean isEmail(String email) {
		return DataUtil.matchByPattern(email,pattern );
	}
}
