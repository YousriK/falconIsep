package com.falcon.avisep.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.falcon.avisep.security.exception.MissingAuthorisation;
import com.falcon.avisep.security.exception.NoValideAuthorisation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryError implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
    {
        if(authException instanceof MissingAuthorisation)
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Authorisation Missing");
        }
        else if(authException instanceof NoValideAuthorisation)
        {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Token is Not Valid");
        }
    }
}
