package com.example.springproject.controller.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementHandler(HttpServletRequest req, NoSuchElementException e){
        String err = "No such element found: " + req.getRequestURI();
        return responseException(new ExceptionResponse(HttpStatus.NOT_FOUND, err));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> badRequestHandler(HttpServletRequest req, ConstraintViolationException e){
        String err = "Validation exception. Seek for the cause: " + e.getMessage();
        return responseException(new ExceptionResponse(HttpStatus.BAD_REQUEST, err));
    }

    private ResponseEntity<Object> responseException(ExceptionResponse response){
        return new ResponseEntity<Object>(response, response.getHttpStatus());
    }
}
