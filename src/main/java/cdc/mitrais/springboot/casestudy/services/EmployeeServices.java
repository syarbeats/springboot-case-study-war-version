package cdc.mitrais.springboot.casestudy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cdc.mitrais.springboot.casestudy.dao.EmployeeRepository;
import cdc.mitrais.springboot.casestudy.dao.EmployeeRepositoryWithPaging;
import cdc.mitrais.springboot.casestudy.model.Employee;


@Service
public class EmployeeServices {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private EmployeeRepositoryWithPaging empRepoWithPaging;
	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<Employee> getEmployeeList(int page, int size){
		
		return Lists.newArrayList(empRepoWithPaging.findAll(new PageRequest(page,size)));
	}
	
	@Transactional
	public List<Employee> getEmployeeListWithoutPaging(){
		
		return Lists.newArrayList(empRepo.findAll());
	}
	
	@Transactional
	public Employee getEmployeeById(int id) {
		return empRepo.findById(id).get();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public Employee updateEmployeeSalary(Employee emp) {
		empRepo.save(emp);
		return emp;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public Employee addEmployee(Employee emp) {
		return empRepo.save(emp);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void deleteEmployee(Employee emp) {
		 empRepo.delete(emp);
	}
}
