package com.improveit.simpleapp.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

@Component
public class RuleForPhonenumber implements Rule {

	@Autowired
	private UserDao userDao;
	
	public String valid(String value, UserDao userDao) {
		String error = null;
		if(value.matches("^\\d{11}$")) {
			if(userDao.getFirst("phonenumber", value) != null)
				error = "That number already exists in database, try another one";
		} else {
			error = "Must be valid phonenumber like '89135433445'";
		}
		return error;
	}

}
