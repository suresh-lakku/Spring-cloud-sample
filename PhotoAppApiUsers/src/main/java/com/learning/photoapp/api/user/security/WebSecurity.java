package com.learning.photoapp.api.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learning.photoapp.api.user.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private Environment enviroment;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public WebSecurity(Environment enviroment,
			UsersService usersService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.enviroment = enviroment;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(enviroment.getProperty("gateway.ipaddress"))
		.and().addFilter(getAuthenticationFilers());
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilers() throws Exception {
		AuthenticationFilter filter = new AuthenticationFilter(usersService, enviroment, authenticationManager());
		filter.setFilterProcessesUrl(enviroment.getProperty("login.url.path"));
		return filter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}

	
	
}
