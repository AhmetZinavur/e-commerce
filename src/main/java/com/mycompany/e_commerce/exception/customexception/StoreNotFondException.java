package com.mycompany.e_commerce.exception.customexception;

public class StoreNotFondException extends RuntimeException {
    public CustomeException exception;

    public StoreNotFondException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
