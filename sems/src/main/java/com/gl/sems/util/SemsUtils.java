/**
 *
 */
package com.gl.sems.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Sems Utils class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
public class SemsUtils {

	/**
	 * To get Request Send Role
	 *
	 * @return String
	 */
	public static final String getRequestSenderRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> requestSenderRole = authentication.getAuthorities();
		return requestSenderRole.toString();
	}
}
