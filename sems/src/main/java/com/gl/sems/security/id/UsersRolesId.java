/**
 *
 */
package com.gl.sems.security.id;

import java.util.Objects;

import jakarta.persistence.Column;

/**
 * UserRoles bridge ID class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
public class UsersRolesId {

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "role_id")
	private Integer roleId;

	@Override
	public int hashCode() {
		return Objects.hash(roleId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UsersRolesId)) {
			return false;
		}
		UsersRolesId other = (UsersRolesId) obj;
		return Objects.equals(roleId, other.roleId) && Objects.equals(userId, other.userId);
	}

}
