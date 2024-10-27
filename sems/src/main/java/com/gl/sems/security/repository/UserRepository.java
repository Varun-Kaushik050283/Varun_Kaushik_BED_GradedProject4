/**
 * Package for User repository
 */
package com.gl.sems.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.sems.security.entity.User;

/**
 * User repository to fetch all user records
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User getUserByUsername(String username);
}
