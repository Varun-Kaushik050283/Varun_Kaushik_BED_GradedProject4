package com.gl.sems.service;

import com.gl.sems.domain.EmployeeDto;
import com.gl.sems.domain.ResponseDto;

/**
 * Employee Service interface
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
public interface EmployeeService {

	/**
	 *
	 * @return
	 */
	public Object findAll();

	/**
	 *
	 * @return
	 */
	public Object findAll(String sortOrder);

	/**
	 *
	 * @param id
	 * @return
	 */
	public Object findById(int id);

	/**
	 *
	 * @param employeeDto
	 * @return
	 */
	public ResponseDto saveEmployee(EmployeeDto employeeDto);

	/**
	 *
	 * @param id
	 * @return
	 */
	public ResponseDto deleteById(int id);

	/**
	 *
	 * @param firstName
	 * @return
	 */
	public Object searchByFirstName(String firstName);

	/**
	 *
	 * @param employeeDto
	 * @return
	 */
	public Object updateEmployee(EmployeeDto employeeDto);
}
