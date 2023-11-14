package com.academy.springlv3.exception;

public class EmailDuplicatedException extends RuntimeException {

    public EmailDuplicatedException(String message) {
        super(message);
    }
}
