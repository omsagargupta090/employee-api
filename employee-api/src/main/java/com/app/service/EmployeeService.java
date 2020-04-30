package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {

	public int saveEmployee(Employee employee);
	public Employee getEmployee(int id);
	public int deleteEmployee(int id);
	public int updateEmployee(Employee employee);
	public List<Employee> getAllEmployees();
}
