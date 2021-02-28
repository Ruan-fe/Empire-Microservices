package com.empire.products.configurations.exceptions;

public class UsernameAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistException(String msg) {
        super(msg);
    }

    public UsernameAlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
