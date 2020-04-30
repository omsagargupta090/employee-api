package com.app.repo;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeRepo {

	public int save(Employee employee);
	public Employee findById(int id);
	public int deleteById(int id);
	public int updateById(Employee employee);
	public List<Employee> findAll();
	
}
