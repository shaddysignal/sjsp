package com.improveit.simpleapp.services.rules.interfaces;

import com.improveit.simpleapp.dao.UserDao;

public interface Rule {

	/**
	 * Push through userDao, theoretically - every rule need it.
	 * 
	 * @param value
	 * @param userDao
	 * @return
	 */
	String valid(String value, UserDao userDao);
	
}
