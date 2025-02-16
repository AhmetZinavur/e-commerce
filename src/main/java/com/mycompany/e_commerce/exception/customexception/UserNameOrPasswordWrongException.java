package com.mycompany.e_commerce.exception.customexception;

public class UserNameOrPasswordWrongException extends RuntimeException {
    public CustomeException exception;

    public UserNameOrPasswordWrongException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
