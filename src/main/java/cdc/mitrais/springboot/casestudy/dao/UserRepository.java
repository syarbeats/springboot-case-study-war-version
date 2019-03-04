package cdc.mitrais.springboot.casestudy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cdc.mitrais.springboot.casestudy.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
}
