package com.mycompany.e_commerce.exception.customexception;

public class TokenExpiredException extends RuntimeException {
    public CustomeException exception;

    public TokenExpiredException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }
}
