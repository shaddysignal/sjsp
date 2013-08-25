package com.improveit.simpleapp.intreface;

import java.util.List;

import com.improveit.simpleapp.model.User;

public interface IUserDao {
	
	int updateOrCreate(User user);
	void remove(User existingUser);
	User getUnique(String paramName, String paramValue);
	List<User> getAllUsers();
	
}
