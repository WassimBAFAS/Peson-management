package com.bfs.crud.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bfs.crud.rest.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository <Employee, Long> , CrudRepository<Employee, Long> {

}
