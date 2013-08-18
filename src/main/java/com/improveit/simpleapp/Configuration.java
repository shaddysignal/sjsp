package com.improveit.simpleapp;

import org.springframework;

@Configuration
public class Configuration {

	@Bean
	@Scope(value=singleton)
	public DispaqtcherServlet dispatcherServlet() { return new DispatcherServlet(); }

}
