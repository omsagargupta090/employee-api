package com.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/get-string")
	public String getString(){
		return "Hello World";
	}
	
	
	@PostMapping("/save-employee")
	public int saveEmployee(@RequestBody Employee employee){
		
		logger.info("Start EmployeeController :: saveEmployee() ::"+employee);
		int status = service.saveEmployee(employee);

		logger.info("End EmployeeController :: saveEmployee() ::"+employee);
		return status;
	}
	
	
	@PostMapping("/update-employee")
	public int updateEmployee(@RequestBody Employee employee){
		
		logger.info("Start EmployeeController :: updateEmployee() ::"+employee);
		
        int status = service.updateEmployee(employee);
		
		logger.info("END EmployeeController :: updateEmployee() :: "+employee);
		
		return status;
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public int deleleEmployee(@PathVariable("id") int id){
		
		logger.info("Start EmployeeController :: deleleEmployee() ::"+id);
		
		int status = service.deleteEmployee(id);
		
		logger.info("END EmployeeController :: deleleEmployee() ::"+id);
		
		return status;
	}
	
	@GetMapping("/get-employee")
	public Employee getEmployee(@RequestParam("id") int id){
		
		logger.info("Start EmployeeController :: getEmployee() ::"+id);
		
		Employee employee = service.getEmployee(id);
		if(employee==null)  
			throw new EmployeeNotFoundException("id: "+ id);  
		
		logger.info("END EmployeeController :: getEmployee() ::"+id);
		return employee;
	}
	
	@GetMapping("/get-all")
	public List<Employee> getEmployees(){
		
		logger.info("Start EmployeeController :: getEmployees() ::");
		List<Employee> employees = service.getAllEmployees();
		logger.info("END EmployeeController :: getEmployees() ::");
		return employees;
	}
	
}
