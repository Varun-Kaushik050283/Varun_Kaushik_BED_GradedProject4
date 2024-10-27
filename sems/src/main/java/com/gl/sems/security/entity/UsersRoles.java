/**
 *
 */
package com.gl.sems.security.entity;

import com.gl.sems.security.id.UsersRolesId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * UsersRoles bridge entity class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Entity
@Table(name = "users_roles", schema = "sems")
@IdClass(UsersRolesId.class)
@Data
public class UsersRoles {

	@Id
	private Long userId;

	@Id
	private Integer roleId;

	/**
	 * Default Constructor
	 */
	public UsersRoles() {
	}

	/**
	 * @param userId
	 * @param roleId
	 */
	public UsersRoles(Long userId, Integer roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

}
