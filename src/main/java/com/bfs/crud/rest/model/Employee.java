package com.bfs.crud.rest.model;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Table(name="Employee")
@Entity
@DynamicInsert
@DynamicUpdate
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(nullable = false)
	private String Nom;
	@Column(nullable = false)
	private String Prenom;
	@Column
	private String email;
	@Column
	private LocalDate DateNaissance;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateNaissance() {
		return DateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		DateNaissance = dateNaissance;
	}

	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", email=" + email + ", DateNaissance="
				+ DateNaissance + "]";
	}

	public Employee(Long id, String nom, String prenom, String email, LocalDate dateNaissance) {
		super();
		Id = id;
		Nom = nom;
		Prenom = prenom;
		this.email = email;
		DateNaissance = dateNaissance;
	}

	public Employee() {

	}

}
