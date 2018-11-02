package com.aldrich.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.portal.dao.EmployeeDAO;
import com.aldrich.portal.entity.Employee;
import com.aldrich.portal.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	EmployeeDAO employeeDao;

	public boolean saveEmployee() 
	{
		boolean status=true;
		Employee employee=new Employee();
		try
		{
			
			employee.setEmpId("1");
			employee.setAddress("Hyderabad");
			employee.setEmpName("Ashok");
			employee.setTechnology("java");
			employeeDao.save(employee);
		}
		catch(Exception e)
		{
			status=false;
			e.printStackTrace();
		}
		return status;
	}

}
