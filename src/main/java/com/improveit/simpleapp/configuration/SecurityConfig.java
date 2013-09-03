package com.improveit.simpleapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.improveit.simpleapp.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = encoder();
		auth
			.userDetailsService(userService)
			.passwordEncoder(encoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/res/**", "/js/**", "/css/**");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager bean = super.authenticationManagerBean();
		return bean;
	}
		
	@Bean
	public StandardPasswordEncoder encoder() {
		return new StandardPasswordEncoder("SomeSecret");
	}	
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler("/successDefine.kitty");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeUrls()
				.antMatchers("/define.kitty",
						"/next_step.kitty",
						"/finale.kitty",
						"/error.kitty",
						"/step/first.kitty"
					).permitAll()
				.antMatchers("/step/finale.kitty").authenticated()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginUrl("/define.kitty").permitAll()
				.loginProcessingUrl("/define.kitty")
				.failureUrl("/error.kitty")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler())
				.and()
			.logout()
				.logoutUrl("/undefine.kitty")
				.logoutSuccessUrl("/define.kitty")
				.and()
			.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/define.kitty");
	}
	
}
