package com.mx.webserviceemployees.dao;

import com.mx.webserviceemployees.entity.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobDAO extends CrudRepository<Job, Integer> {
}
