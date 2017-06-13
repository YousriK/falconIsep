package com.falcon.avisep.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
@ControllerAdvice
public class GlobalExceptionConfiguration {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ClientSMSError handleConflict(HttpServletResponse res, UsernameNotFoundException e) {
        return new ClientSMSError(res, e);
    }

}
