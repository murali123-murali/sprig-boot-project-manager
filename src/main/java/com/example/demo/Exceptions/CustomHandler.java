package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CustomHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectIdException.class)
    public ResponseEntity<?> handleProjectIdException(ProjectIdException ex, HttpServletRequest request) {
        ProjectIdExceptionResponse response = new ProjectIdExceptionResponse(ex.getMessage());
        System.out.println("response is ::"+response);
        return new ResponseEntity<ProjectIdExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

}
