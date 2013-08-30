package com.improveit.simpleapp.services;

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
	 * Delegate to validator do job
	 * 
	 * @param parameters key -> rule and value -> valueToTest
	 * @return errors like key -> rule and value -> error
	 */
	public Map<String, String> validate(Map<String, String> parameters) {
		userSession.getStep().addExclusiveRules(parameters);
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
		int newUserId = userDao.updateOrCreate(user);
		userSession.setUserId(newUserId);
		user.setId(newUserId);
		return newUserId;
	}
	
	public void setUser(User user, Steps step) {
		userSession.setUserId(user.getId());
		userSession.setStep(step);
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
	 * Delegate method to dao layer
	 * 
	 * @param params
	 * @return
	 */
	public List<User> getUsersByParams(Map<String, String> params) {
		return userDao.getByParams(params);
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
		User user = userDao.getFirst(userSession.getUserId());		
		return user == null ? new User() : user;
	}
	
	public void setUserStep(Steps step) {
		userSession.setStep(step);
	}
	
	public Steps getUserStep() {
		return userSession.getStep();
	}
	
}
