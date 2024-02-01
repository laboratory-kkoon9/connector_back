package com.connector.global.exception;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException() {
        super("Invalid Email");
    }
}