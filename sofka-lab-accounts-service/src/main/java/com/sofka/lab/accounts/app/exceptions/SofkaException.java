package com.sofka.lab.accounts.app.exceptions;

import org.springframework.http.HttpStatus;

public class SofkaException extends Exception {

    private String message;
    private HttpStatus httpStatus;

    public SofkaException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
