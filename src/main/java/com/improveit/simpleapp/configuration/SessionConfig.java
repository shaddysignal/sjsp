package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.UserSession;
import com.improveit.simpleapp.services.UserService;

@Configuration
public class SessionConfig {
	
	@Bean
	@Scope
	public IUserDao userDao() {
		UserDao userDao = new UserDao();		
		return userDao;
	}
	
	@Bean
	@Scope
	public UserSession userScope() {
		UserSession scope = new UserSession();
		return scope;
	}
	
	@Bean
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public UserService userService() {
		return new UserService();
	}
	
}
