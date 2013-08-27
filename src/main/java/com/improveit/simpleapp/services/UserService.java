package com.improveit.simpleapp.services;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.model.Steps;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.model.UserSession;
import com.improveit.simpleapp.services.validators.UserValidator;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserValidator userValidator;	
	
	/**
	 * 
	 * @param parameters key -> rule and value -> valueToTest
	 * @return errors like key -> rule and value -> error
	 */
	public Map<String, String> validate(Map<String, String> parameters) {
		return userValidator.valid(parameters);
	}
	
	/**
	 * Delegate create to dao layer.
	 * If user exist - update it.
	 * Also refresh session user.
	 * 
	 * @param user to insert
	 */
	public int putUser(User user) {
		userSession.setUserId(userDao.updateOrCreate(user));
		return userSession.getUserId();
	}
	
	public void userDone() {
		userDao.setUserDone(userSession.getUserId());
	}
	
	/**
	 * Delegate select all users to dao layer.
	 * 	
	 * @return all registered users
	 */
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	/**
	 * Delegate remove to dao layer.
	 * 
	 * @param user to remove
	 */
	public void deleteUser(User user) {
		userDao.remove(user);
	}
	
	public User getCurrentUser() {
		User user = userDao.getFirst("id", userSession.getUserId().toString());		
		return user == null ? new User() : user;
	}
	
	public void setUserStep(Steps step) {
		userSession.setStep(step);
	}
	
	public Steps getUserStep() {
		return userSession.getStep();
	}
	
}
