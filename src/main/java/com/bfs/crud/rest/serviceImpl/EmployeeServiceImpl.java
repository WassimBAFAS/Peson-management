package com.bfs.crud.rest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfs.crud.rest.dao.EmployeeRepository;
import com.bfs.crud.rest.exception.ResourceNotFoundException;
import com.bfs.crud.rest.model.Employee;
import com.bfs.crud.rest.model.dto.EmployeeDTO;
import com.bfs.crud.rest.service.EmployeeService;



@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
    private ModelMapper mapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees(//Integer pageNo, Integer pageSize, String sortBy
			) {
		// TODO Auto-generated method stub
		 //Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		// List<Employee> employees=  employeeRepository.findAll(pageable).getContent();
		List<Employee> employees=  (List<Employee>) employeeRepository.findAll();
		 if(employees.size()==0)
			 throw new ResourceNotFoundException("Employee table is empty!");
		 return employees.stream()
				         .map(employee -> mapper.map(employee, EmployeeDTO.class))
				         .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = mapper.map(employeeDTO, Employee.class);
		employeeRepository.save(employee);

	}

	@Override
	@Transactional
	public void updateEmployee(EmployeeDTO employeeDTO) {
		Employee employee = mapper.map(employeeDTO, Employee.class);
		employeeRepository.findById(employee.getId()).map(e -> {
			e.setNom(employee.getNom());
			e.setPrenom(employee.getPrenom());
			e.setEmail(employee.getEmail());
			e.setDateNaissance(employee.getDateNaissance());
			return employeeRepository.save(e);
		});

	}

	@Override
	@Transactional
	public void deleteEmployee(EmployeeDTO employeeDTO) {
		Employee employee = mapper.map(employeeDTO, Employee.class);
		employeeRepository.delete(employee);

	}

	@Override
	public EmployeeDTO getById(Long id) throws ResourceNotFoundException {
		
		Employee employee =  employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
	    
		return mapper.map(employee, EmployeeDTO.class);
	}

}
