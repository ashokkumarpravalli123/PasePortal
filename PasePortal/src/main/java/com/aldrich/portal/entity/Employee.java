package com.aldrich.portal.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
@Document(collection = "persons")
public class Employee 
{
	@Id
	private String Id;
	
	@Field("emp_id")
	@Getter
	@Setter
	private String empId;
	
	@Field("emp_name")
	@Getter
	@Setter
	private String empName;
	
	@Field("address")
	@Getter
	@Setter
	private String address;
	
	@Field("technology")
	@Getter
	@Setter
	private String technology;

}
