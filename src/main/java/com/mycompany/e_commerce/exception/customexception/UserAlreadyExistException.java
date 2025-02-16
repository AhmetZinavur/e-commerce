package com.mycompany.e_commerce.exception.customexception;

public class UserAlreadyExistException extends RuntimeException {
    public CustomeException exception;

    public UserAlreadyExistException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
