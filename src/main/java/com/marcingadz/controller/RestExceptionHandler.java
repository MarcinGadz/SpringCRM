package com.marcingadz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handle(CustomerNotFoundException e) {
        CustomerErrorResponse r = new CustomerErrorResponse();
        r.setMessage(e.getMessage());
        r.setStatus(HttpStatus.NOT_FOUND.value());
        r.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAll(Exception e) {
        CustomerErrorResponse r = new CustomerErrorResponse();
        r.setMessage(e.getMessage());
        r.setStatus(HttpStatus.BAD_REQUEST.value());
        r.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }
}
