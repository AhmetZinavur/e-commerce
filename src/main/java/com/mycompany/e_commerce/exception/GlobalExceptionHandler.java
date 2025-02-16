package com.mycompany.e_commerce.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mycompany.e_commerce.exception.customexception.CustomeException;
import com.mycompany.e_commerce.exception.customexception.OrderNotFoundException;
import com.mycompany.e_commerce.exception.customexception.ProductAlreadyExistsException;
import com.mycompany.e_commerce.exception.customexception.ProductNotFoundException;
import com.mycompany.e_commerce.exception.customexception.StoreAlreadyExistsException;
import com.mycompany.e_commerce.exception.customexception.StoreNotFondException;
import com.mycompany.e_commerce.exception.customexception.TokenExpiredException;
import com.mycompany.e_commerce.exception.customexception.TokenInvalidException;
import com.mycompany.e_commerce.exception.customexception.UnauthorizedAccessException;
import com.mycompany.e_commerce.exception.customexception.UserAlreadyExistException;
import com.mycompany.e_commerce.exception.customexception.UserNameOrPasswordWrongException;
import com.mycompany.e_commerce.exception.customexception.UserNotFoundException;
import com.mycompany.e_commerce.exception.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ExceptionResponse response(CustomeException customeException) {
        return ExceptionResponse.builder()
                .statusCode(customeException.getStatusCode())
                .message(customeException.getMessage())
                .timeStamp(LocalDateTime.now().toString())
                .build();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    public ExceptionResponse handleOrderNotFoundException(OrderNotFoundException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseBody
    public ExceptionResponse handleProductAlreadyExistsException(ProductAlreadyExistsException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ExceptionResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(StoreAlreadyExistsException.class)
    @ResponseBody
    public ExceptionResponse handleStoreAlreadyExistsException(StoreAlreadyExistsException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(StoreNotFondException.class)
    @ResponseBody
    public ExceptionResponse handleStoreNotFoundException(StoreNotFondException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public ExceptionResponse handleTokenExpiredException(TokenExpiredException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(TokenInvalidException.class)
    @ResponseBody
    public ExceptionResponse handleTokenInvalidException(TokenInvalidException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ExceptionResponse handleUnauthorizedAccessException(UnauthorizedAccessException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ExceptionResponse handleUserAlreadyExistException(UserAlreadyExistException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(UserNameOrPasswordWrongException.class)
    @ResponseBody
    public ExceptionResponse handleUserNameOrPasswordWrongException(UserNameOrPasswordWrongException exception) {
        return response(exception.exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException exception) {
        return response(exception.exception);
    }
}