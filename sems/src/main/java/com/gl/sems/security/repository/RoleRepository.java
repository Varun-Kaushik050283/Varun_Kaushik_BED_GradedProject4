/**
 *
 */
package com.gl.sems.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.sems.security.entity.Role;

/**
 * Role Repository class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role getRoleByName(String name);
}
