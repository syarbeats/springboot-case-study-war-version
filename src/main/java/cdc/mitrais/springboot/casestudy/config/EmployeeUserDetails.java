package cdc.mitrais.springboot.casestudy.config;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cdc.mitrais.springboot.casestudy.model.User;



public class EmployeeUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	private Logger logger = LoggerFactory.getLogger(EmployeeUserDetails.class);
	
	public EmployeeUserDetails(User user) {
		super();
		this.user = user;
		logger.info("EmployeeUserDetails is invoking....");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = user.getRole();
		System.out.println("Role: "+ role);
		logger.info("getAuthorities from EmployeeUserDetails is invoking....");
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		logger.info("getPassword from EmployeeUserDetails is invoking....");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		logger.info("getUsername from EmployeeUserDetails is invoking....");
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
