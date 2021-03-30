package com.saleservice.configurations.exceptions;

public class ProductException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
