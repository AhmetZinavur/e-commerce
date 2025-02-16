package com.mycompany.e_commerce.exception.customexception;

public class UserNotFoundException extends RuntimeException {
    public CustomeException exception;

    public UserNotFoundException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
