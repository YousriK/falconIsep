package com.falcon.avisep.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class ErrorControllerHandler {

	private final Logger logger = LoggerFactory.getLogger(ErrorControllerHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleException(NullPointerException e) {
    	logger.error(e.toString());
        return new ResponseEntity<>(new ErrorMessage("The request is not correct"), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
