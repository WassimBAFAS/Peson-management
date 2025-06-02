package com.bfs.crud.rest.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
public class EmployeeDTO {

	private Long Id;

	@NotBlank(message = "{nom.notempty}")
	private String Nom;
	@NotBlank(message = "{prenom.notempty}")
	private String Prenom;
	@NotBlank(message = "{email.notempty}")
	@Email(message = "{email.notvalid}")
	private String email;
	private LocalDate DateNaissance;




}
