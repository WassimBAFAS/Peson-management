package com.bfs.crud.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bfs.crud.rest.exception.ResourceNotFoundException;
import com.bfs.crud.rest.model.dto.EmployeeDTO;
import com.bfs.crud.rest.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "CRUD REST APIs for Employee Resource", description = "CRUD REST APIs - Create Employee, Update Employee, Get Employee, Get All Employee, Delete Employee")
@RestController
@RequestMapping(value = "/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Fetches all employees from the system
	 * 
	 * @return List<EmployeeDTO> containing all employee records
	 * @apiNote This endpoint returns all employees with pagination
	 * @param pageNo   The number of page
	 * @param pageSize The number of records to return per page
	 * @param sortBy   The field to sort the results by
	 */

	@Operation(description = "fetch all Employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@RequestMapping(method = RequestMethod.GET)
	public List<EmployeeDTO> getAllEmployees(
//			@RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortBy
	) {
		return employeeService.getAllEmployees(// pageNo, pageSize, sortBy
		);
	}

	@Operation(description = "fetch Employee by Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@Parameter(description = "The user ID") @PathVariable Long id)
			throws ResourceNotFoundException {
		return employeeService.getById(id);
	}

	@Operation(description = "Add an Employees")
	@PostMapping
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
	public void addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
	}

	@PutMapping
	@Operation(summary = "Update employee ", description = "Update employee  REST API is used to delete a particular employee from the database")
	@ApiResponse(responseCode = "202", description = "HTTP Status 202  SUCCESS")
	public void updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.updateEmployee(employeeDTO);
	}

	@DeleteMapping
	@Operation(summary = "Delete Employee", description = "Delete Employee REST API is used to delete a particular Employee from the database")
	@ApiResponse(responseCode = "202 ", description = "HTTP Status 202  SUCCESS")
	public void deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.deleteEmployee(employeeDTO);
	}

}
