/**
 * Package for entity class
 */
package com.gl.sems.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Role entity class for authorization requirement
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Entity
@Table(name = "roles", schema = "sems")
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	/**
	 * Default constructor
	 */
	public Role() {
	}

	/**
	 * @param name
	 */
	public Role(String name) {
		super();
		this.name = name;
	}

}
