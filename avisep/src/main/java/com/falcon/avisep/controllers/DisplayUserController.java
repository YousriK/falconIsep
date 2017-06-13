package com.falcon.avisep.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller 
public class DisplayUserController {

    private final Logger log = LoggerFactory.getLogger(DisplayUserController.class);

    @RequestMapping("/displayU")
	public String displayU(Model model){
    	
		return "displayUser";
	}
 // Creating some users
 	@RequestMapping(value = "saveUserAvis", method = RequestMethod.POST )
 	public String saveUser(@RequestParam("firstN") String firstN, @RequestParam("lastN")String lastN, 
 			@RequestParam("login") String login,@RequestParam("email") String email,@RequestParam("password") String password, Model model){
 		
// 		RoleAvis roleAvis=RoleAvis.ROLE_ADMIN;
// 		UserAvis userAvis =new UserAvis();
// 		//login,firstN,email, password,roleAvis,
// 		userAvis.setLogin(login);
// 		userAvis.setFirstName(firstN);
// 		userAvis.setEmail(email);
// 		userAvis.setPasswd(password);
// 		userAvis.addRoleAvis(roleAvis);
// 		userService.save(userAvis);
 		//new EmailService().enviar(nome, email);
 		
 		//Iterable<UserAvis> userAvis1 = userService.findAll();
 		
 		//model.addAttribute("userAvis", userAvis1);
 		
 		return "displayUser";
 	}
    
}