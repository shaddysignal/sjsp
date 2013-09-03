package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.improveit.simpleapp.model.UserSession;

@Configuration
@ComponentScan({"com.improveit.simpleapp.services"})
public class SessionConfig {
	
	@Bean
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public UserSession userSession() {
		UserSession scope = new UserSession();
		return scope;
	}
	
}
