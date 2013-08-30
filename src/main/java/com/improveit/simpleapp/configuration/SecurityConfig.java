package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().
			withUser("user").password("user").roles("USER").and().
			withUser("admin").password("admin").roles("ADMIN", "USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/res/**", "/js/**", "/css/**");
	}
	
	/*@Bean
	public BasicAuthenticationEntryPoint entryPoint() {
	    BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
	    basicAuthEntryPoint.setRealmName("Realm of Kittens");
	    return basicAuthEntryPoint;
	}*/
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager bean = super.authenticationManagerBean();
        return bean;
    }
	
	@Bean
	public StandardPasswordEncoder encoder() {
		return new StandardPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http			
			//.exceptionHandling()
			//	.authenticationEntryPoint(entryPoint())
			//	.and()
			.authorizeUrls()
				.antMatchers("/define.kitty", "/undefine.kitty", "/first.kitty", "/next_step.kitty").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginUrl("/define.kitty").permitAll()
				.loginProcessingUrl("/define.kitty")
				.failureUrl("/error.kitty")
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/undefine.kitty")
				.logoutSuccessUrl("/define.kitty")
				.and()
			.rememberMe()
				.and()
			.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/define.kitty");
	}
	
}
