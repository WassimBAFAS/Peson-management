package com.bfs.crud.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RestCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCrudApplication.class, args);
	}
	
	

}
