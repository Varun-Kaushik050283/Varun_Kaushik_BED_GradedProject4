/**
 * Package for repository
 */
package com.gl.sems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.sems.entity.Employee;

/**
 * Employee repository class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.firstName = ?1")
	public List<Employee> getEmployeesByFirstName(String firstName);

	@Query("SELECT e FROM Employee e WHERE e.email = ?1")
	public Employee getEmployeeByEmail(String email);

	@Query("SELECT e FROM Employee e ORDER BY e.firstName ASC")
	public List<Employee> getEmployeesInAscendingOrder();

	@Query("SELECT e FROM Employee e ORDER BY e.firstName DESC")
	public List<Employee> getEmployeesInDescendingOrder();
}
