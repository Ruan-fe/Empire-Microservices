package com.empire.authentication.configurations.exceptions;

public class UserAuthException extends RuntimeException {

    public UserAuthException(String message) {
        super(message);
    }

    public UserAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
