package com.spring100.studentservice.errors;

import org.springframework.http.HttpStatus;

public class ResponseError {
    private String message;
    private String code;
    public ResponseError(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public ResponseError(String message) {
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
