package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.UserSession;

@Configuration
@ComponentScan(basePackages={"com.improveit.simpleapp.controller", "com.improveit.simpleapp.services"})
public class SimpleServletConfig {
	
}
