package com.mycompany.e_commerce.exception.customexception;

public class TokenInvalidException extends RuntimeException {
    public CustomeException exception;

    public TokenInvalidException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
