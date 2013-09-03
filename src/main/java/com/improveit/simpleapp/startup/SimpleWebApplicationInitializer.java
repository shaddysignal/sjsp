package com.improveit.simpleapp.startup;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.improveit.simpleapp.configuration.Config;
import com.improveit.simpleapp.configuration.SimpleServletConfig;

public class SimpleWebApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext =
				new AnnotationConfigWebApplicationContext();
		rootContext.register(Config.class);
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
				
		AnnotationConfigWebApplicationContext dispatcherContext = 
				new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(SimpleServletConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"simpleServlet", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.kitty");
				
	}

}
