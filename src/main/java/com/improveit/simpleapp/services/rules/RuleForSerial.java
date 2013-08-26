package com.improveit.simpleapp.services.rules;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForSerial implements Rule {

	public String valid(String value, UserDao userDao) {
		String error = value.matches("^\\d{4}$") ? null : "Must be 4 digits";
		return error;
	}

}
