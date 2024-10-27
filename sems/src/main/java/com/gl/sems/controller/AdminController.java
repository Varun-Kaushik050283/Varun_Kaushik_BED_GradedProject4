/**
 * Package for controller classes
 */
package com.gl.sems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.sems.domain.ResponseDto;
import com.gl.sems.domain.UserDto;
import com.gl.sems.security.entity.Role;
import com.gl.sems.security.service.SemsAdminService;
import com.gl.sems.util.SemsUtils;

/**
 * Administrator controller class
 *
 * @author Varun Kaushik
 * @apiNote Secured Employee Management System(SEMS) API
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	/**
	 * Constants
	 */
	private static final String SADMIN_ROLE_AUTHORIZED = "[SADMIN]";

	/**
	 * Additional required Services declarations and Initializations
	 */
	@Autowired
	private SemsAdminService semsAdminService;

	/**
	 * Objective 1. End point to add roles (i.e... ADMIN/USER/SADMIN) from Super
	 * admin user (varun)
	 *
	 * @param role
	 * @return
	 */
	@PostMapping("/add/role")
	public ResponseDto addRole(@RequestBody Role role) {
		// To check whether the sender of request is SUPER ADMIN or NOT
		if (!SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("User is not authorized to send the request.");
		}

		return (null != role.getName()) ? semsAdminService.addRole(role)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

	/**
	 * Object 2. End point to add users having different roles (i.e...
	 * ADMIN/USER/SADMIN) from Super admin user (varun)
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("/add/user")
	public ResponseDto addUser(@RequestBody UserDto user) {

		// To check whether the sender of request is SUPER ADMIN or NOT
		if (!SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("User is not authorized to send the request.");
		}

		return ((null != user.getUsername()) && (null != user.getPassword()) && (null != user.getRoles()))
				? semsAdminService.addUser(user)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

}
