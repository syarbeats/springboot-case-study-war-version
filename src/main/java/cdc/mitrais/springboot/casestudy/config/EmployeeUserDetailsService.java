package cdc.mitrais.springboot.casestudy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cdc.mitrais.springboot.casestudy.dao.UserRepository;
import cdc.mitrais.springboot.casestudy.model.User;



public class EmployeeUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(EmployeeUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername from EmployeeUserDetailsService is invoking....");
		User user = userRepository.findByUsername(username);
		
		logger.info("Username: "+user.getUsername()+", Role: "+user.getRole());
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new EmployeeUserDetails(user);
	}

}
