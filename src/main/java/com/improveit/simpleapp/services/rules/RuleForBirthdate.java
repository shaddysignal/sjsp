package com.improveit.simpleapp.services.rules;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForBirthdate implements Rule {

	public String valid(String value, UserDao userDao) {
		String error = value.matches("^\\d{4}-\\d{2}-\\d{2}$") ? null : "Must be in format like this - 'yyyy-mm-dd'";
		return error;
	}

}
