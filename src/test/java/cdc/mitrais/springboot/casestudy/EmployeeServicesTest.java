package cdc.mitrais.springboot.casestudy;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cdc.mitrais.springboot.casestudy.config.EmployeeUserDetailsService;
import cdc.mitrais.springboot.casestudy.dao.EmployeeRepository;
import cdc.mitrais.springboot.casestudy.model.Employee;
import cdc.mitrais.springboot.casestudy.services.EmployeeServices;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServicesTest {

	@Autowired
	private EmployeeUserDetailsService employeeUserDetailsService;
	@Autowired
	private EmployeeServices employeeService;
	@Autowired
	private EmployeeRepository empRepo;
	
	private UserDetails userDetails;
	private Authentication authToken;
	
	@Before
	public void setUp() throws Exception {
		userDetails = employeeUserDetailsService.loadUserByUsername ("admin");
        authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	@Test
	@Transactional
    @Rollback(true)
	public void testAddEmployee() {
		
		employeeService.addEmployee(new Employee(909, "Elthon John", 75000));
		Assert.assertEquals("Elthon John", employeeService.getEmployeeById(909).getName());
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testEditEmployee() {
		/*
		 * Employee with id=406, name=Elvis Presley, salary=55000
		 * **/
		Employee emp = employeeService.getEmployeeById(406); 
		emp.setSalary(60000);/*Update his salary to 60000*/
		employeeService.updateEmployeeSalary(emp);
		Assert.assertEquals(60000, employeeService.getEmployeeById(406).getSalary());
	}
	
	@Test
	@Transactional
	public void testSearchEmployee() {
		/*
		 * Employee with id=406, name=Elvis Presley, salary=55000
		 * **/
		Employee emp = employeeService.getEmployeeById(406); 
		Assert.assertEquals(55000, employeeService.getEmployeeById(406).getSalary());
	}
	
	@Test(expected = NoSuchElementException.class)
	@Transactional
    @Rollback(true)
	public void testDeleteEmployee() {
		/*
		 * Employee with id=406, name=Elvis Presley, salary=55000
		 * **/
		Employee emp = employeeService.getEmployeeById(406); 
		employeeService.deleteEmployee(emp);
		Employee emp2 = employeeService.getEmployeeById(406);
	}

}
