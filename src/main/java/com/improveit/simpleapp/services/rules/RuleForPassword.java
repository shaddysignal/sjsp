package com.improveit.simpleapp.services.rules;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForPassword implements Rule {

	public String valid(String value, UserDao userDao) {
		String error = value.matches("^.{5,}$") ? null : "Must be more than 5 sumbols"; 
		return error;
	}

}
