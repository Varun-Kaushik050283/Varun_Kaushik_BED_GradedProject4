/**
 * Package for entity classes
 */
package com.gl.sems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Employee Entity class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Entity
@Table(name = "employees", schema = "sems")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	/**
	 * Default Constructor
	 */
	public Employee() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
