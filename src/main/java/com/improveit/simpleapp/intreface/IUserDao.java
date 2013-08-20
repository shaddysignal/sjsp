package com.improveit.simpleapp.intreface;

import com.improveit.simpleapp.model.User;

public interface IUserDao {

	void setup();
	void destroy();
	
	void create(User newUser);
	void update(User existingUser);
	void remove(User existingUser);
	User get(String paramName, String paramValue);
	
}
