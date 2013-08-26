package com.improveit.simpleapp.services.rules;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForNumber implements Rule {

	public String valid(String value, UserDao userDao) {
		String error = value.matches("^\\d{6}$") ? null : "Must be 6 digits";
		return error;
	}

}
