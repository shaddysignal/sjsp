package com.improveit.simpleapp.intreface;

import com.improveit.simpleapp.model.User;

public interface IUserDao {
	
	int create(User newUser);
	int update(User existingUser);
	void remove(User existingUser);
	User getUnique(String paramName, String paramValue);
	
}
