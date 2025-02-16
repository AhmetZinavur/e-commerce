package com.mycompany.e_commerce.exception.customexception;

public class StoreAlreadyExistsException extends RuntimeException {
    public CustomeException exception;

    public StoreAlreadyExistsException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
