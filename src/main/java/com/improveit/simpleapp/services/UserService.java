package com.improveit.simpleapp.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.model.Steps;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.model.UserSession;
import com.improveit.simpleapp.services.validators.UserValidator;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserValidator userValidator;
	
	private static Logger logger = Logger.getLogger(UserService.class);
	
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
	 * Implementation of interface UserDetailService.
	 * For Spring Security.
	 */
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getFirst("email", email);
		if(user == null)
			throw new UsernameNotFoundException("Error login name(your email).");
		logger.info("Start to process of check passwords.");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(1);
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails details = new org.springframework.security.core.userdetails.User(
				email,
				user.getPassword(),
				authorities
			);
		return details;
	}
	
	/**
	 * Delegate create to dao layer.
	 * If user exist - update it.
	 * Also refresh session user.
	 * 
	 * @param user to insert
	 */
	public int mergeUser(User user) {
		int newUserId = userDao.updateOrCreate(user);
		userSession.setUserId(newUserId);
		user.setId(newUserId);
		return newUserId;
	}
	
	/**
	 * Set current session user to full registred.
	 */
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
	 * Another bridge, for specific param
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public User getFirstUser(String paramName, String paramValue) {
		return userDao.getFirst(paramName, paramValue);
	}
	
	/**
	 * Delegate remove to dao layer.
	 * 
	 * @param user to remove
	 */
	public void deleteUser(User user) {
		userDao.remove(user);
	}
	
	/**
	 * Get current user from session.
	 * 
	 * @return current user from session
	 */
	public User getCurrentUser() {
		User user = userDao.getFirst(userSession.getUserId());		
		return user == null ? new User() : user;
	}
	
	/**
	 * Set current user for session.
	 * 
	 * @param user
	 */
	public void setCurrentUser(User user) {
		userSession.setUserId(user.getId());
		userSession.setStep(user.getStep());
	}
	
	public void setUserStep(Steps step) {
		userSession.setStep(step);
	}
	
	public Steps getUserStep() {
		return userSession.getStep();
	}
	
}
