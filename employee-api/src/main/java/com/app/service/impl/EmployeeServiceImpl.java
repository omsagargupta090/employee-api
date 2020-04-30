package com.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repo.EmployeeRepo;
import com.app.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public int saveEmployee(Employee employee) {
		
		logger.info("Start EmployeeServiceImpl :: saveEmployee() ::"+employee);
		int status = repo.save(employee);
		logger.info("End EmployeeServiceImpl :: saveEmployee() ::"+employee);
		return status;
	}

	@Override
	public Employee getEmployee(int id) {
		
		logger.info("Start EmployeeServiceImpl :: getEmployee() ::"+id);
		Employee employee = repo.findById(id);
		logger.info("End EmployeeServiceImpl :: getEmployee() ::"+id);
		return employee;
	}

	@Override
	public int deleteEmployee(int id) {
		
		logger.info("Start EmployeeServiceImpl :: deleteEmployee() ::"+id);
		int status = repo.deleteById(id);
		logger.info("End EmployeeServiceImpl :: deleteEmployee() ::"+id);
		return status;
	}

	@Override
	public int updateEmployee(Employee employee) {
		
		logger.info("Start EmployeeServiceImpl :: updateEmployee() ::"+employee);
		int status = repo.updateById(employee);
		logger.info("End EmployeeServiceImpl :: updateEmployee() ::"+employee);
		return status;
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Start EmployeeServiceImpl :: getAllEmployees() ::");
		List<Employee> employees = repo.findAll();
		logger.info("End EmployeeServiceImpl :: getAllEmployees() ::");
		return employees;
	}

}
