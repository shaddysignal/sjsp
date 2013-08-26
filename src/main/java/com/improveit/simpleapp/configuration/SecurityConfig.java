package com.improveit.simpleapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeUrls().
			antMatchers("/define", "/undefine", "/first").permitAll().
			antMatchers("/admin/**").hasRole("ADMIN").
			anyRequest().authenticated().and().
			formLogin().loginUrl("/define").permitAll().loginProcessingUrl("/next_step").and().
			rememberMe();
	}
	
}
