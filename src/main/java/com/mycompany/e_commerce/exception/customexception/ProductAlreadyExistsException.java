package com.mycompany.e_commerce.exception.customexception;

public class ProductAlreadyExistsException extends RuntimeException {
    public CustomeException exception;

    public ProductAlreadyExistsException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
