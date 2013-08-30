package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.model.UserSession;
import com.improveit.simpleapp.services.UserService;

@Configuration
public class SessionConfig {
	
	@Bean
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public UserDao userDao() {
		UserDao userDao = new UserDao();		
		return userDao;
	}
	
	@Bean
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public UserSession userSession() {
		UserSession scope = new UserSession();
		return scope;
	}
	
	@Bean	
	public UserService userService() {
		return new UserService();
	}
	
}
