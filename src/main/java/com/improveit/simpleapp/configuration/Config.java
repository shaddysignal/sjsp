package com.improveit.simpleapp.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.validators.UserValidator;

@Import({ SessionConfig.class, SecurityConfig.class })
@Configuration
@ComponentScan(basePackages = {
		"com.improveit.simpleapp.controller",
		"com.improveit.simpleapp.services",
		"com.improveit.simpleapp.services.validators"
	})
@EnableWebMvc
@EnableTransactionManagement
@PropertySource({ "classpath:hibernate.properties", "classpath:jdbc.properties" })
public class Config extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan(new String[] {"com.improveit.simpleapp.model", "com.improveit.simpleapp.services"});
		bean.setHibernateProperties(getHibernateProperties());
		return bean;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager bean = new HibernateTransactionManager();
		bean.setSessionFactory(sessionFactory);
		return bean;
	}
	
	@Bean
	public Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
		prop.put("hibernate.hbm2dll.auto", env.getProperty("hibernate.hbm2dll.auto"));
		return prop;
	}

	@Bean
	@Autowired
	public UserValidator validator(UserDao userDao) {
		UserValidator bean = new UserValidator();
		bean.setUserDao(userDao);
		return bean;
	}
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver bean = new SimpleMappingExceptionResolver();
		bean.setDefaultErrorView("error");
		Properties mappings = new Properties();
		mappings.put(".DataAccessException", "error");
		mappings.put(".NoSuchRequestHandlingMethodException", "error");
		mappings.put(".TypeMismatchException", "error");
		mappings.put(".MissingServletRequestParameterException", "error");
		bean.setExceptionMappings(mappings);
		return bean;
	}

}
