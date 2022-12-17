package com.mx.webserviceemployees.dao;

import com.mx.webserviceemployees.entity.Gender;
import org.springframework.data.repository.CrudRepository;

public interface GenderDAO extends CrudRepository<Gender,Integer> {
}
