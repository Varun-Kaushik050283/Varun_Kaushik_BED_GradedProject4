/**
 *
 */
package com.gl.sems.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * RoleDto class to carry consolidated data across layers
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Data
public class RoleDto implements Serializable {

	/**
	 * Properties Declaration
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	/**
	 * Default constructor
	 */
	public RoleDto() {
	}

	/**
	 *
	 * @param id
	 * @param name
	 */
	public RoleDto(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
