package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.intreface.IUserDao;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.model.UserScope;
import com.improveit.simpleapp.services.UserService;

@Configuration
@ComponentScan(basePackages={"com.improveit.simpleapp.controller"})
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("*.css")
			.addResourceLocations("/resources/css/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("*.js")
			.addResourceLocations("/resources/js/")
			.setCachePeriod(31556926);
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {		
        configurer.enable();
    }
	  
	@Bean(initMethod="init", destroyMethod="destroy")
	@Scope("session")
	public IUserDao dao() {
		return new UserDao();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	@Scope("session")
	public UserScope userScope() {
		UserScope scope = new UserScope();
		scope.setUser(new User());
		scope.setStep(0);
		return scope;
	}
	
	@Bean
	public UserService userValidateService() {
		return new UserService();
	}
}
