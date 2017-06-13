package com.falcon.avisep.controllers;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.falcon.avisep.util.utilMethod;
/**
 * REST controller for managing UserAvis.
 */
@Controller 
public class WelcomeAdminController {

    private final Logger log = LoggerFactory.getLogger(WelcomeAdminController.class);
    // Create and display forms
    @RequestMapping(value={"/","welcomeA"})
	public String welcomAd(Model model,HttpServletRequest request){
    	
		return utilMethod.getBaseUrl(request).equalsIgnoreCase("welcomeA")?"welcomeAdmin":"redirect:index";
	}
    
}
