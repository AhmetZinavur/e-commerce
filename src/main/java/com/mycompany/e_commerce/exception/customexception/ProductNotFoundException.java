package com.mycompany.e_commerce.exception.customexception;

public class ProductNotFoundException extends RuntimeException {
    public CustomeException exception;

    public ProductNotFoundException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
