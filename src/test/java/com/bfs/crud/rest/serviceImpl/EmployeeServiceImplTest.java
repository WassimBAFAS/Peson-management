package com.bfs.crud.rest.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.bfs.crud.rest.dao.EmployeeRepository;
import com.bfs.crud.rest.model.Employee;
import com.bfs.crud.rest.model.dto.EmployeeDTO;

import nonapi.io.github.classgraph.utils.Assert;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
	
	  @InjectMocks
	  EmployeeServiceImpl service;

	  @Mock
	  EmployeeRepository dao;
	  
	  @Spy
	    private ModelMapper mapper;

//	  @BeforeEach
//	  public void init() {
//	    MockitoAnnotations.openMocks(this);
//	  }

	@Test
	void testGetAllEmployees() {
		Employee employee = Employee.builder()
        		.Nom("bafas")
        		.Prenom("wassim")
        		.DateNaissance(LocalDate.now())
        		.email("wassim.bfs@gmail.com")
        		.build();
		Employee employee2 = Employee.builder()
        		.Nom("bafas")
        		.Prenom("simo")
        		.DateNaissance(LocalDate.now())
        		.email("simo.bfs@gmail.com")
        		.build();
		
		List<Employee> employees = new ArrayList<Employee>() {{
			add(employee);
			add(employee2);
		}};
		Mockito.when(dao.findAll()).thenReturn(employees);
		List<EmployeeDTO> allEmployee= service.getAllEmployees();
		assertEquals("wassim", allEmployee.get(0).getPrenom());
		assertEquals("simo", allEmployee.get(1).getPrenom());
	}

	@Test
	void testAddEmployee() {
		EmployeeDTO employeeDTO = EmployeeDTO.builder()
        		.Nom("bafas")
        		.Prenom("wassim")
        		.DateNaissance(LocalDate.now())
        		.email("wassim.bfs@gmail.com")
        		.build();
		service.addEmployee(employeeDTO);
		Mockito.verify(dao, Mockito.times(1)).save(Mockito.any());
		
	}

	@Test
	void testUpdateEmployee() {
		Employee employee = Employee.builder()
        		.Nom("bafas")
        		.Prenom("wasim")
        		.DateNaissance(LocalDate.now())
        		.email("wassimbfs@gmail.com")
        		.build();
		Optional<Employee> empOp= Optional.of(employee);
				
		EmployeeDTO employeeDTO = EmployeeDTO.builder()
				.Id(1l)
        		.Nom("bafas")
        		.Prenom("wassim")
        		.DateNaissance(LocalDate.now())
        		.email("wassim.bfs@gmail.com")
        		.build();
		Mockito.when(dao.findById(anyLong())).thenReturn(empOp);
		service.updateEmployee(employeeDTO);
		Mockito.verify(dao, Mockito.times(1)).findById(Mockito.any());
		Mockito.verify(dao, Mockito.times(1)).save(Mockito.any(Employee.class));
	}

	@Test
	void testDeleteEmployee() {
		EmployeeDTO employeeDTO = EmployeeDTO.builder()
				.Id(1l)
        		.Nom("bafas")
        		.Prenom("wassim")
        		.DateNaissance(LocalDate.now())
        		.email("wassim.bfs@gmail.com")
        		.build();
		service.deleteEmployee(employeeDTO);
		//Mockito.verify(dao, Mockito.times(1)).findById(Mockito.any());
		Mockito.verify(dao, Mockito.times(1)).delete(Mockito.any(Employee.class));
		
	}

	@Test
	void testGetById() {
		Employee employee = Employee.builder()
        		.Nom("bafas")
        		.Prenom("wassim")
        		.DateNaissance(LocalDate.now())
        		.email("wassim.bfs@gmail.com")
        		.build();
		Optional<Employee> empOp= Optional.of(employee);
		
		Mockito.when(dao.findById(anyLong())).thenReturn(empOp);
		
		EmployeeDTO employeeDTO = service.getById(1l);
		assertEquals("bafas", employeeDTO.getNom());
		assertEquals("wassim", employeeDTO.getPrenom());
		
	}

}
