package com.project.banking.services.impl;

import java.text.DecimalFormat;

public class UserValidation {

	static DecimalFormat df = new DecimalFormat("0.00");
	
	public static boolean isValidSSN(String ssn) {
		if (ssn != null && ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidState(String state) {
		if (state != null && state.matches("[A-Z]{2}")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidUsername(String username) {
		if (username != null && username.matches("[a-zA-Z0-9]{5,20}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidPassword(String password) {
		if (password != null && password.matches("[a-zA-Z0-9]{5,20}")) {
			return true;
		} else {
			return false;
		}
	}
	

	public static boolean isValidDOB(String dob) {
		if (dob != null && dob.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
			return true;
		} else {
			return false;
		}
	}
	
}
