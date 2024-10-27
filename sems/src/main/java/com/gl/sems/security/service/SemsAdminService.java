/**
 * Package to store all the security services
 */
package com.gl.sems.security.service;

import com.gl.sems.domain.ResponseDto;
import com.gl.sems.domain.UserDto;
import com.gl.sems.security.entity.Role;

/**
 * Sems Admin Service Interface
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
public interface SemsAdminService {

	/**
	 *
	 * @param Role
	 */
	public ResponseDto addRole(Role role);

	/**
	 *
	 * @param user
	 * @return
	 */
	public ResponseDto addUser(UserDto user);
}
