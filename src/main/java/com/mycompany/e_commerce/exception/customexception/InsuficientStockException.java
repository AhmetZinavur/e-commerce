package com.mycompany.e_commerce.exception.customexception;

public class InsuficientStockException extends RuntimeException {
    public CustomeException exception;
    
    public InsuficientStockException(CustomeException exception) {
        super(exception.getMessage());
        this.exception = exception;
    }

}
