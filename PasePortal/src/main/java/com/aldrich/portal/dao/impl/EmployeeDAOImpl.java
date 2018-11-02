package com.aldrich.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.aldrich.portal.dao.EmployeeDAO;
import com.aldrich.portal.entity.Employee;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO 
{

	@Autowired
	MongoTemplate mongoTemplate;

	public void save(Employee employeeDetails) {
		
		mongoTemplate.insert(employeeDetails);
	}

}
