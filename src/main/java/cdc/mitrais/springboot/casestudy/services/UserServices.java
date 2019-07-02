package cdc.mitrais.springboot.casestudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cdc.mitrais.springboot.casestudy.dao.UserRepository;
import cdc.mitrais.springboot.casestudy.model.User;

@Service
public class UserServices {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public User CreateUser(User user) {
		User userData = user;
		
		userData.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		return userRepository.save(userData);
	}
}
