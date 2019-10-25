package com.mockproject.du1.common;

public class EmailValidate {

	private static final String pattern = "/^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";

	public static boolean isEmail(String email) {
		return DataUtil.matchByPattern(email, pattern);
	}
}
