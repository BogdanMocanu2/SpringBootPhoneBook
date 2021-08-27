package com.bogdan.demo.exceptions;

public class OveridingContactException extends RuntimeException {

    public OveridingContactException(String message) {
        super(message);
    }

    public OveridingContactException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
