package com.bogdan.demo.exceptions;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
