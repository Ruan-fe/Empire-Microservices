package com.saleservice.configurations.exceptions;

public class SaleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SaleException(String message) {
        super(message);
    }

    public SaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
