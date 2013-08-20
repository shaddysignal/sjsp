package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.improveit.simpleapp.controller"})
public class SimpleServletConfig {
	
}
