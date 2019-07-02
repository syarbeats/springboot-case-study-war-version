package cdc.mitrais.springboot.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CaseStudyApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(CaseStudyApplication.class);
	 }

}
