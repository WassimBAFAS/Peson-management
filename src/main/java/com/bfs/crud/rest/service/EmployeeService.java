package com.bfs.crud.rest.service;

import java.util.List;

import com.bfs.crud.rest.exception.ResourceNotFoundException;
import com.bfs.crud.rest.model.dto.EmployeeDTO;

public interface EmployeeService {

	//List<EmployeeDTO> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
			
	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getById(Long id) throws ResourceNotFoundException;

	void addEmployee(EmployeeDTO employeeDTO);

	void updateEmployee(EmployeeDTO employeeDTO);

	void deleteEmployee(EmployeeDTO employeeDTO);

}
