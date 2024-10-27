/**
 *
 */
package com.gl.sems.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * UserDto class to carry consolidated User data across layers
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Data
public class UserDto implements Serializable {

	/**
	 * Properties Declaration
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private List<RoleDto> roles = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public UserDto() {
	}

	/**
	 * @param username
	 * @param password
	 * @param roles
	 */
	public UserDto(String username, String password, List<RoleDto> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

}
