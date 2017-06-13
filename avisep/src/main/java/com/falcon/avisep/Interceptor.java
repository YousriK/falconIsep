package com.falcon.avisep;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws IOException{
		String uri = request.getRequestURI();
		if((uri.endsWith("login") || uri.contains("resources")) && request.getSession().getAttribute("userLogged")==null){
			return true;
		}
		
		if(request.getSession().getAttribute("userLogged")!=null && uri.endsWith("login")){
			response.sendRedirect("/index");
			return true;
		}
		
		if(request.getSession().getAttribute("userLogged")!=null){
			return true;
		}
		else{
			response.sendRedirect("/login");
			return false;
		}
	}
}
