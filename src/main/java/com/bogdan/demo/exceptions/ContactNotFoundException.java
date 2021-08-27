package com.bogdan.demo.exceptions;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
