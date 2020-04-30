package com.app.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.repo.EmployeeRepo;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

	private static final Logger logger = LogManager.getLogger(EmployeeRepoImpl.class);
	
	public static final String INSERT_EMP = "insert into employee101 values(?,?,?,?)";
	public static final String SELECT_EMP = "select * from employee101 where id = ?"; 
	public static final String DELETE_EMP = "delete from employee101 where id = ?";
	public static final String SELECT_ALL = "select * from employee101";
	public static final String UPDATE_EMP = "update employee101 set name=?, age=?, salary=?  where id=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Employee employee) {
		
		logger.info("Start EmployeeRepoImpl :: getEmployee() ::"+employee);
		int status = jdbcTemplate.update(INSERT_EMP, employee.getId(),employee.getName(),employee.getAge(),employee.getSalary());
		logger.info("End EmployeeRepoImpl :: getEmployee() ::"+employee);
		return status;
	}

	@Override
	public Employee findById(int id) {
		
		logger.info("Start EmployeeRepoImpl :: findById() ::"+id);
		Employee employee = null;
    	
		
		try{
		employee = (Employee) jdbcTemplate.queryForObject(SELECT_EMP, new Object[] { id }, new EmployeeMapper());
		
		}catch(Exception ex){
			throw new EmployeeNotFoundException("Data Is Not Available.");
		}
		logger.info("End EmployeeRepoImpl :: findById() ::"+id);
		return employee;
	}

	@Override
	public int deleteById(int id) {
		
		logger.info("Start EmployeeRepoImpl :: deleteById() ::"+id);
		int status = 0;
		try{
		   status = jdbcTemplate.update(DELETE_EMP,id);
		}catch(Exception ex){
			throw new EmployeeNotFoundException("Data is not Available for delete Operation");
		}
		
		if(status==0){
			throw new EmployeeNotFoundException("Data is not Deleted cause Not Available");
		}
		
		logger.info("End EmployeeRepoImpl :: deleteById() ::"+id);
		return status;
	}

	@Override
	public int updateById(Employee employee) {
		logger.info("Start EmployeeRepoImpl :: updateById() ::"+employee);	
		 int status = 0;
		 
		 try{
		 status = jdbcTemplate.update(UPDATE_EMP
                 , employee.getName()
                 , employee.getAge()
                 , employee.getSalary()
                 , employee.getId());
		 }catch(Exception ex){
			 throw new EmployeeNotFoundException("Data is not available specific id"+employee.getId());
		 }
		 
		 logger.info("End EmployeeRepoImpl :: updateById() ::"+employee);
		return status;
	}

	@Override
	public List<Employee> findAll() {
		
		logger.info("Start EmployeeRepoImpl :: findAll() ::");
         List<Employee> employees = new ArrayList<Employee>();
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL);

         for (Map<String, Object> row : rows) 
         {
        	 Employee employee = new Employee();
        	 employee.setId((int)row.get("id"));
        	 employee.setName((String)row.get("name"));
        	 employee.setAge((int)row.get("age"));
        	 employee.setSalary((int)row.get("salary"));

             employees.add(employee);
          }
         
         if(employees.isEmpty()){
        	 throw new EmployeeNotFoundException("Employee response");
         }
       logger.info("End EmployeeRepoImpl :: findAll() ::");
        return employees;
	}

}


class EmployeeMapper implements RowMapper {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setAge(rs.getInt("age"));
		employee.setSalary(rs.getInt("salary"));
		return employee;
	}
}