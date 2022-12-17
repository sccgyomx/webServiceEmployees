package com.mx.webserviceemployees.dao;

import com.mx.webserviceemployees.entity.EmployeeWorkedHours;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeWorkedHoursDAO extends CrudRepository<EmployeeWorkedHours, Integer> {
}
