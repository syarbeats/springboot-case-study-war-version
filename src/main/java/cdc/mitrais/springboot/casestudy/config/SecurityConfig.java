package cdc.mitrais.springboot.casestudy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		logger.info("DaoAuthentication process.....");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		logger.info("set BCrypt Password Encoder.....");
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		logger.info("set EmployeeUserDetailsService.....");
	    return new EmployeeUserDetailsService();
	}
}
