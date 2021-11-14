package com.learning.photoapp.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;
	
	@Autowired
	public WebSecurity(Environment environment) {
		super();
		this.environment = environment;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		.antMatchers(environment.getProperty("user.api.actuator.path")).permitAll()
		.antMatchers(environment.getProperty("zuul.actuator.url.path")).permitAll()
		.antMatchers(environment.getProperty("h2.console.path")).permitAll()
		.antMatchers(HttpMethod.POST, environment.getProperty("user.api.registration.path")).permitAll()
		.antMatchers(HttpMethod.POST, environment.getProperty("user.api.login.path")).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new AuthorizationFilter(authenticationManager(), environment));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	
}
