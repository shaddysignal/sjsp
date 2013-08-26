package com.improveit.simpleapp.services.rules;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForSecondName implements Rule {

	public String valid(String value, UserDao userDao) {
		return null;
	}

}
