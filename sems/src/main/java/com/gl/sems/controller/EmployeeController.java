/**
 * Package for controller
 */
package com.gl.sems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.sems.domain.EmployeeDto;
import com.gl.sems.domain.ResponseDto;
import com.gl.sems.service.EmployeeService;
import com.gl.sems.util.SemsUtils;

/**
 * Employee Controller class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	/**
	 * Constants
	 */
	private static final String SADMIN_ROLE_AUTHORIZED = "[SADMIN]";
	private static final String ADMIN_ROLE_AUTHORIZED = "[ADMIN]";

	/**
	 * Additional required Services declarations and Initializations
	 */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Object 3. End point to add employees from only ADMIN role
	 *
	 * @param employeeDto
	 * @return
	 */
	@PostMapping("/add")
	public ResponseDto addEmployee(@RequestBody EmployeeDto employeeDto) {

		// To check whether the sender of request is ADMIN or NOT
		if (!SemsUtils.getRequestSenderRole().equalsIgnoreCase(ADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("Only ADMIN user is authorized to send the request.");
		}

		return ((null != employeeDto.getFirstName()) && (null != employeeDto.getLastName())
				&& (null != employeeDto.getEmail())) ? employeeService.saveEmployee(employeeDto)
						: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

	/**
	 * Object 4. End point to list employees from ADMIN/USER roles
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/list")
	public Object fetchEmployees() {

		// To check whether the sender of request is SADMIN or NOT
		if (SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("SUPER ADMIN user is NOT authorized to send the request.");
		}
		return employeeService.findAll();
	}

	/**
	 * Object 5. End point to find an employee from ADMIN/USER roles via employee ID
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/find/{employeeId}")
	public Object fetchEmployeeById(@PathVariable("employeeId") Integer employeeId) {

		// To check whether the sender of request is SADMIN or NOT
		if (SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("SUPER ADMIN user is NOT authorized to send the request.");
		}

		return (null != employeeId) ? employeeService.findById(employeeId)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");

	}

	/**
	 * Object 6. End point to update an employee's details from only ADMIN role
	 *
	 * @param employeeDto
	 * @return
	 */
	@PutMapping("/update")
	public Object updateEmployee(@RequestBody EmployeeDto employeeDto) {

		// To check whether the sender of request is ADMIN or NOT
		if (!SemsUtils.getRequestSenderRole().equalsIgnoreCase(ADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("Only ADMIN user is authorized to send the request.");
		}

		return ((null != employeeDto.getFirstName()) && (null != employeeDto.getLastName())
				&& (null != employeeDto.getEmail())) ? employeeService.updateEmployee(employeeDto)
						: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

	/**
	 * Object 7. End point to delete an employee from only ADMIN role
	 *
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("/delete/{employeeId}")
	public ResponseDto deleteEmployee(@PathVariable("employeeId") Integer employeeId) {

		// To check whether the sender of request is ADMIN or NOT
		if (!SemsUtils.getRequestSenderRole().equalsIgnoreCase(ADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("Only ADMIN user is authorized to send the request.");
		}

		return (null != employeeId) ? employeeService.deleteById(employeeId)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

	/**
	 * Object 8. End point to search employees from ADMIN/USER role via employee's
	 * First Name
	 *
	 * @param employeeFirstName
	 * @return
	 */
	@GetMapping("/search/{employeeFirstName}")
	public Object fetchEmployeesByFirstName(@PathVariable("employeeFirstName") String employeeFirstName) {

		// To check whether the sender of request is SADMIN or NOT
		if (SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("SUPER ADMIN user is NOT authorized to send the request.");
		}

		return (null != employeeFirstName) ? employeeService.searchByFirstName(employeeFirstName)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");

	}

	/**
	 * Object 9. End point to list all employees from ADMIN/USER role via ascending
	 * or descending order preference
	 *
	 * @param sortOrder
	 * @return
	 */
	@GetMapping("/sort")
	public Object fetchEmployeesInSortedOrder(@RequestParam("order") String sortOrder) {

		// To check whether the sender of request is SADMIN or NOT
		if (SemsUtils.getRequestSenderRole().equalsIgnoreCase(SADMIN_ROLE_AUTHORIZED)) {
			return new ResponseDto("SUPER ADMIN user is NOT authorized to send the request.");
		}
		return (null != sortOrder) ? employeeService.findAll(sortOrder)
				: new ResponseDto("Request cannot be processed due to insufficient data received. ");
	}

}
