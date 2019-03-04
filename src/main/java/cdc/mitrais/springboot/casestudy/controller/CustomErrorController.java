package cdc.mitrais.springboot.casestudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
	
	@RequestMapping("/error")
	public String handleError(Model model) {
		logger.info("CurrentUsername: "+this.getCurrentUsername());
		model.addAttribute("username", this.getCurrentUsername());
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	public String getCurrentUsername() {
		 
		 String username;
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		 } else {
		   username = principal.toString();
		 }
		 
		 return username;
	 }

}
