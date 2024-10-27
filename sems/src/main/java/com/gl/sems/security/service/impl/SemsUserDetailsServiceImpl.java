/**
 *
 */
package com.gl.sems.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.sems.security.entity.SemsUserDetails;
import com.gl.sems.security.entity.User;
import com.gl.sems.security.repository.UserRepository;

/**
 * Sems User Details Service Implementation class of UserDetailsService
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
public class SemsUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("User not found with respect to the provided username input.");
		}

		return new SemsUserDetails(user);
	}

}
