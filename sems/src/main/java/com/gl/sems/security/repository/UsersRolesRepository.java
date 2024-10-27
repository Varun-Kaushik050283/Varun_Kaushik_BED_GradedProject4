/**
 *
 */
package com.gl.sems.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.sems.security.entity.UsersRoles;
import com.gl.sems.security.id.UsersRolesId;

/**
 * UsersRoles bridge repository class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, UsersRolesId> {

}
