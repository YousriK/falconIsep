package com.falcon.avisep.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.falcon.avisep.util.utilMethod;
/**
 * REST controller for managing UserAvis.
 */
@Controller 
public class IndexController {
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		
		return "redirect:"+utilMethod.getBaseUrl(request);
	}
}
