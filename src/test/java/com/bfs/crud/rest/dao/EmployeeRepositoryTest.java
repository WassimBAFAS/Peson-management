package com.bfs.crud.rest.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bfs.crud.rest.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@AfterAll
	void clean() {
	
		//employeeRepository.deleteAll();
		
	}

	@Test
	void testFindById() {

		Employee employee = Employee.builder()
							.Nom("bafas19")
							.Prenom("wassim10")
							.DateNaissance(LocalDate.now())
							.email("wassim.bfs10@gmail.com")
							.build();
		employeeRepository.save(employee);
		assertEquals("wassim10", employeeRepository.findById(1l).get().getPrenom());

	}

}
