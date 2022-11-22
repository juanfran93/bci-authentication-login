package com.globallogic.bci.auth.login.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?!=.*\\d{2})[A-Za-z\\d@$!%*?&]{8,12}$";

	public static boolean patternMatches(String charSec, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(charSec)
	      .matches();
	}
	
	public static boolean emailMatches(String email) {
		return patternMatches(email, EMAIL_REGEX);
	}
	
	public static boolean passwordMatches(String password) {
		return patternMatches(password, PASSWORD_REGEX);
	}
}
