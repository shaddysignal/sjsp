package com.improveit.simpleapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.model.UserSession;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSession userSession;
	
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
	
	public void setUserStep(String step) {
		userSession.setStep(step);
	}
	
	public String getUserStep() {
		return userSession.getStep();
	}
	
}
