package com.marcingadz.controller;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String s) {
        super(s);
    }

    public CustomerNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CustomerNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
