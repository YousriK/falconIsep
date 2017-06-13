package com.falcon.avisep.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.falcon.avisep.model.Admin;
import com.falcon.avisep.model.ETeacher;
import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.Student;
import com.falcon.avisep.model.VTeacher;
import com.falcon.avisep.service.UserAvisServiceImpl;
@Controller 
public class LoginController  {

	private final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    private com.falcon.avisep.service.AuthService authService;
	@Autowired
	private UserAvisServiceImpl userService;
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:/login";
	}
	@RequestMapping(value= "login", method = RequestMethod.POST)
	public String login(HttpSession session,@RequestParam("login") String login, @RequestParam("passwd") String passwd) throws Exception{
		String token=authService.generateToken(login, passwd);
		Role role=userService.findByToken(token).getRole();
		System.out.println(role+" "+login+" "+passwd);
		if(role.name().equalsIgnoreCase(Role.STUDENT.name())){
			Student userLog=(Student) userService.findByToken(token);
			session.setAttribute("userLogged", userLog);
			return "redirect:/";
		}else if(role.name().equalsIgnoreCase(Role.VTEACHER.name())){
			VTeacher userLog=(VTeacher) userService.findByToken(token);
			session.setAttribute("userLogged", userLog);
			return "redirect:/";
		}else if(role.name().equalsIgnoreCase(Role.ETEACHER.name())){
			ETeacher userLog=(ETeacher)userService.findByToken(token);
			session.setAttribute("userLogged", userLog);
			return "redirect:/";
		}else if(role.name().equalsIgnoreCase(Role.ADMIN.name())){
			Admin userLog=(Admin) userService.findByToken(token);
			session.setAttribute("userLogged", userLog);
			return "redirect:/";
		}
		return null;
	}
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}