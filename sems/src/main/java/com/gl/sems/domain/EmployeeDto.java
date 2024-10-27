/**
 * Package for entity classes
 */
package com.gl.sems.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * Employee Entity class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Data
public class EmployeeDto implements Serializable {

	/**
	 * Properties declaration
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String email;

	/**
	 * Default Constructor
	 */
	public EmployeeDto() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public EmployeeDto(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
