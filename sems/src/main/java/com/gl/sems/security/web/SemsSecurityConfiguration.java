/**
 *
 */
package com.gl.sems.security.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.sems.security.service.impl.SemsUserDetailsServiceImpl;

/**
 * SEMS Security configuration class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Configuration
public class SemsSecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new SemsUserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider ssrsDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers("/sems/admin/add/role").hasAuthority("SADMIN")
				.requestMatchers("/sems/admin/add/user").hasAuthority("SADMIN")
				.requestMatchers(HttpMethod.POST, "/sems/employees/*").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/sems/employees/*").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/sems/employees/*").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/sems/employees/*").hasAnyAuthority("USER", "ADMIN").anyRequest()
				.authenticated().and().httpBasic().and().cors().and().csrf().disable();

		http.authenticationProvider(ssrsDaoAuthenticationProvider());
		return http.build();
	}

}
