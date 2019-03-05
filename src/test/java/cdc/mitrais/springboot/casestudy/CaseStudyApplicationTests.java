package cdc.mitrais.springboot.casestudy;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import cdc.mitrais.springboot.casestudy.config.EmployeeUserDetailsService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class CaseStudyApplicationTests {

	private final String NAME_FOR_ID_404 = "Keith Richard";
	private final int SALARY_FOR_ID_404 = 45000;
	private final int ID = 404;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			 MediaType.APPLICATION_JSON.getSubtype(),Charset.forName("utf8"));
	private MockMvc mockMvc; 
	private UserDetails userDetails;
	private Authentication authToken;
	
	@Autowired
	private EmployeeUserDetailsService employeeUserDetailsService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Test
	public void contextLoads() {
	}
	
	 @Before
	 public void setup() throws Exception {
		 userDetails = employeeUserDetailsService.loadUserByUsername ("admin");
	     authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	     SecurityContextHolder.getContext().setAuthentication(authToken);
		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
	 } 
	 
	 /*REST API INTEGRATION TESTING
	  * **/
	 
	 @Test
	 public void findEmployeeById() throws Exception {
		 mockMvc.perform(get("/api/employee?id=" + ID))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(contentType))
		 /*NAME MUST BE THE SAME WITH NAME_FOR_ID_404 (Keith Richard)*/
		 .andExpect(jsonPath("$['name']",containsString(NAME_FOR_ID_404)))
		 /*SALARY MUST BE THE SAME WITH SALARY_FOR_ID_404 (45000)*/
		 .andExpect(jsonPath("$['salary']").value(SALARY_FOR_ID_404));
	 } 
	 
	 @Test
	 public void getEmployeeForPage1Size10() throws Exception {
		 mockMvc.perform(get("/api/employees?page=1&size=10"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(contentType))
		 /*THE RESPONSE MUS BE CONTAIN 10 JSON DATA*/
		 .andExpect(jsonPath("$",iterableWithSize(10))) 
		 /*THE NAME FOR THE FIRST DATA MUST BE THE SAME WITH Brian Eipstin*/
		 .andExpect(jsonPath("$[0]['name']",containsString("Brian Eipstin")))
		 /*THE SALARY FOR THE FIRST DATA MUST BE THE SAME WITH 55000*/
		 .andExpect(jsonPath("$[0]['salary']").value(55000));
	 } 

}
