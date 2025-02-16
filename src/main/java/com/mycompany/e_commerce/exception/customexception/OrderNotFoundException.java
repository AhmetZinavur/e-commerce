package com.mycompany.e_commerce.exception.customexception;

public class OrderNotFoundException extends RuntimeException {
    public CustomeException exception;

    public OrderNotFoundException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }
}
