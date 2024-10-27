/**
 * Package for service
 */
package com.gl.sems.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.sems.domain.EmployeeDto;
import com.gl.sems.domain.ResponseDto;
import com.gl.sems.entity.Employee;
import com.gl.sems.repository.EmployeeRepository;
import com.gl.sems.service.EmployeeService;

/**
 * Employee Service Implementation class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Object findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return (null != employees) ? employees : new ResponseDto("No Employees found the SEMS database to be listed.");
	}

	@Override
	public Object findAll(String sortOrder) {
		List<Employee> employees = null;
		switch (sortOrder) {
		case "asc":
			employees = employeeRepository.getEmployeesInAscendingOrder();
			break;
		case "desc":
			employees = employeeRepository.getEmployeesInDescendingOrder();
			break;
		default:
			return new ResponseDto(
					"Sorting order not recognized by SEMS, Please use 'asc' or 'desc' for asending and descending order respectedly.");
		}
		return (null != employees) ? employees : new ResponseDto("No Employees found the SEMS database to be listed.");
	}

	@Override
	public Object findById(int id) {
		Employee employee = null;
		try {
			// find employee record.
			employee = employeeRepository.findById(id).get();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			LOGGER.error(ex.getStackTrace()[0] + "" + ex.toString());
			if (ex.getMessage().contains("No value present")) {
				return new ResponseDto(
						"Exception occurred while finding employee [Root cause:{Employee ID not found in the SEMS database}].");
			} else {
				return new ResponseDto("Exception occurred while finding employee in the SEMS system.");
			}
		}
		return employee;
	}

	@Override
	public ResponseDto saveEmployee(EmployeeDto employeeDto) {

		Employee existingEmployee = employeeRepository.getEmployeeByEmail(employeeDto.getEmail());
		boolean employeeAlreadyExists = (null != existingEmployee);

		if (!employeeAlreadyExists) {

			// convert employee DTO to employee entity
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeDto, employee);

			Employee employeeSaved = null;

			try {
				// Save employee record.
				employeeSaved = employeeRepository.save(employee);
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage());
				LOGGER.error(ex.getStackTrace()[0] + "" + ex.toString());
				return new ResponseDto("Exception occurred while saving " + employee.getFirstName()
						+ "as an employee in the SEMS system.");
			}
			if (null != employeeSaved) {
				return new ResponseDto(employee.getFirstName() + " added successfully in the SEMS system.");
			} else {
				return new ResponseDto(employee.getFirstName() + " not added into the SEMS system.");
			}
		} else {
			return new ResponseDto("Employee already exists, please try with different credentials.");
		}
	}

	@Override
	public ResponseDto deleteById(int id) {

		// To Fetch the employee from the SEMS database on the basis of ID
		Object returnedObj = this.findById(id);

		if (returnedObj instanceof ResponseDto) {

			return (ResponseDto) returnedObj;

		} else if (returnedObj instanceof Employee) {

			Employee employee = (Employee) returnedObj;

			if (null != employee) {

				try {

					// To delete the employee on the basis of employee ID
					employeeRepository.deleteById(id);

				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
					LOGGER.error(ex.getStackTrace()[0] + "" + ex.toString());
					return new ResponseDto(
							"Exception occurred while deleting " + employee.getFirstName() + " from the SEMS system.");
				}

			}

			// To verify whether the employee has been deleted from the SEMS database on the
			// basis of ID
			returnedObj = this.findById(id);

			if (returnedObj instanceof ResponseDto) {
				ResponseDto responseDto = (ResponseDto) returnedObj;
				return (responseDto.toString().contains("Employee ID not found in the SEMS database"))
						? new ResponseDto("Deleted employee id - " + id)
						: responseDto;
			}
		}
		return new ResponseDto("Something went wrong while deleting the employee having ID [" + id + "]");
	}

	@Override
	public Object searchByFirstName(String firstName) {
		List<Employee> employees = null;
		try {
			// search employees by their first name
			employees = employeeRepository.getEmployeesByFirstName(firstName);
			if (employees.isEmpty()) {
				return new ResponseDto(
						"No employees found with the first name as [" + firstName + "] in the SEMS system.");
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			LOGGER.error(ex.getStackTrace()[0] + "" + ex.toString());
			if (ex.getMessage().contains("No value present")) {
				return new ResponseDto(
						"Exception occurred while finding employee [Root cause:{Employee First Name not found in the SEMS database}].");
			} else {
				return new ResponseDto("Exception occurred while searching for employees in the SEMS system.");
			}
		}
		return employees;
	}

	@Override
	public Object updateEmployee(EmployeeDto employeeDto) {

		// To Fetch the employee from the SEMS database on the basis of ID
		Object returnedObj = this.findById(employeeDto.getId());

		if (returnedObj instanceof ResponseDto) {

			return returnedObj;

		} else if (returnedObj instanceof Employee) {

			Employee employee = (Employee) returnedObj;

			if (null != employee) {

				// convert employee DTO to employee entity
				BeanUtils.copyProperties(employeeDto, employee);

				Employee employeeSaved = null;

				try {
					// Update employee record.
					employeeSaved = employeeRepository.save(employee);
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
					LOGGER.error(ex.getStackTrace()[0] + "" + ex.toString());
					return new ResponseDto("Exception occurred while updating " + employeeDto.getFirstName()
							+ "as an employee in the SEMS system.");
				}
				if (null != employeeSaved) {
					return employeeSaved;
				} else {
					return new ResponseDto(employeeDto.getFirstName() + " not updated into the SEMS system.");
				}
			}
		}

		return new ResponseDto("Employee does not exists, please try with different credentials.");
	}

}
