package com.spring100.studentservice.exceptions;

public class InvalidParameterValueException extends RuntimeException {
    public InvalidParameterValueException(String message) {
        super(message);
    }
}
