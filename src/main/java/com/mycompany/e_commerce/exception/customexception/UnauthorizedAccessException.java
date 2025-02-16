package com.mycompany.e_commerce.exception.customexception;

public class UnauthorizedAccessException extends RuntimeException {
    public CustomeException exception;

    public UnauthorizedAccessException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
