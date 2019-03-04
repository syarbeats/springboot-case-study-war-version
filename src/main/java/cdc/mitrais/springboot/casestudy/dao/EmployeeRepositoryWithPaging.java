package cdc.mitrais.springboot.casestudy.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cdc.mitrais.springboot.casestudy.model.Employee;


public interface EmployeeRepositoryWithPaging extends PagingAndSortingRepository<Employee, Integer>  {

}
