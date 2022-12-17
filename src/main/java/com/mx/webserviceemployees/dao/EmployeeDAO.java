package com.mx.webserviceemployees.dao;

import com.mx.webserviceemployees.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee,Integer> {
}
