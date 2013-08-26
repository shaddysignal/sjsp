package com.improveit.simpleapp.services.rules;

import org.springframework.stereotype.Component;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

@Component
public class RuleForEmail implements Rule {

	public String valid(String value, UserDao userDao) {
		String error = null;
		if(value.matches("^[a-zA-Z._0-9]+@[a-zA-Z._0-9]+\\.[a-z]{1,4}$")) {
			if(userDao.getFirst("email", value) != null)
				error = "That email already exists in database, try another one";
		} else
			error = "Must be valid email";
		return error;
	}

}
