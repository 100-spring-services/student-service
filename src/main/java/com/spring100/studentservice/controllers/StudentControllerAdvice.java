package com.spring100.studentservice.controllers;

import com.spring100.studentservice.errors.ResponseError;
import com.spring100.studentservice.exceptions.InvalidParameterValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StudentControllerAdvice {

    @ExceptionHandler(InvalidParameterValueException.class)
    public ResponseEntity<ResponseError> handleInvalidParameterValueException(InvalidParameterValueException ex, WebRequest request) {
        ResponseError error = new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception ex, WebRequest request) {
        ResponseError error = new ResponseError(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
