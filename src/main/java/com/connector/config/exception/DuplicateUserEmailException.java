package com.connector.config.exception;

public class DuplicateUserEmailException extends RuntimeException {
    public DuplicateUserEmailException() {
        super("User already exists");
    }
}
