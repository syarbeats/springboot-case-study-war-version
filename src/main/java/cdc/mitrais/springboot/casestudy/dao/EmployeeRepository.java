package cdc.mitrais.springboot.casestudy.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cdc.mitrais.springboot.casestudy.model.Employee;

@Repository("employeeRepository")
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Query("FROM Employee e WHERE e.name like %:name%")
	public Employee findByName(@Param("name") String name);
}
