package com.improveit.simpleapp.dao;

import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.User;

public class UserDao implements IUserDao {

	public void setup() {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void create(User newUser) {
		// TODO Auto-generated method stub
	}

	public void update(User existingUser) {
		// TODO Auto-generated method stub
	}

	public void remove(User existingUser) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Note: If you want get by serial+number, proceed something like
	 * paramName = "serialnumber", paramValue = "dddddddddd"
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return user by one of unique params, null if none match
	 */
	public User get(String paramName, String paramValue) {
		// TODO Auto-generated method stub
		return new User();
	}

}
