package com.connector.global.exception;

public class DuplicateUserEmailException extends RuntimeException {
    public DuplicateUserEmailException() {
        super("User already exists");
    }
}
