package cdc.mitrais.springboot.casestudy.dao;

import org.springframework.data.repository.CrudRepository;

import cdc.mitrais.springboot.casestudy.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
