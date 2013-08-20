package com.improveit.simpleapp.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.User;

public class UserService {
	
	@Autowired
	private IUserDao userDao;
	
	private boolean validateEmail(String paramValue) {
		if(paramValue.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+\\.[a-z]{1,4}"))
			return true;
		return false;
	}
	
	private boolean validateDate(String paramValue) {
		if(paramValue.matches("\\d{2}-\\d{2}-\\d{4}"))
			return true;
		return false;
	}
	
	private boolean validateUserUniqueParam(String paramName, String paramValue) {
		User someone = userDao.get(paramName, paramValue);
		if(someone == null)
			return true;
		return false;
	}
	
	private boolean validateNumber(String paramValue) {
		if(paramValue.matches("\\d+"))
			return true;
		return false;
	}
	
	public boolean validate(String paramName, String paramValue) {
		boolean ret = false;
		if(paramName.equals("birthdate"))
			ret = validateDate(paramValue);
		if(paramName.equals("email"))
			ret = validateEmail(paramValue) ? validateUserUniqueParam(paramName, paramValue) : false;
		if(paramName.equals("phonenumber"))
			ret = validateNumber(paramValue) ? validateUserUniqueParam(paramName, paramValue) : false;
		if(paramName.equals("serialnumber"))
			ret = validateNumber(paramValue) ? validateUserUniqueParam(paramName, paramValue) : false;
		return ret;
	}
	
	public boolean insertUser(User user) {
		userDao.create(user);
		return true;
	}
	
}
