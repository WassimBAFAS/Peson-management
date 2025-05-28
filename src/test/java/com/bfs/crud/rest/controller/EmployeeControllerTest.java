package com.bfs.crud.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bfs.crud.rest.model.dto.EmployeeDTO;
import com.bfs.crud.rest.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService service;

	@Test
	void greetingShouldReturnMessageFromService() throws Exception {

		EmployeeDTO employee = EmployeeDTO.builder()
				               .Nom("bafas")
				               .Prenom("wassim")
				               .DateNaissance(LocalDate.now())
				               .email("wassim.bfs@gmail.com").build();

		Mockito
		.when(service.getById(Mockito.anyLong()))
		.thenReturn(employee);
		mockMvc.perform(get("/employees/1"))
		       .andExpect(status().isOk())
			   .andExpect(jsonPath("$.prenom", Matchers.is("wassim")));
	}

}
