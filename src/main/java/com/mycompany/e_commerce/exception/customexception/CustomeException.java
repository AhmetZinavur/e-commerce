package com.mycompany.e_commerce.exception.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomeException {

    USER_NOT_FOUND(1001,"User not found"),
    USER_ALREADY_EXISTS(1002, "User already exists"),
    USER_NAME_OR_PASSWORD_IS_WRONG(1003, "User name or password is wrong"),

    STORE_NOT_FOUND(2001, "Store not found"),
    STORE_ALREADY_EXISTS(2002, "Store already exists"),

    TOKEN_EXPIRED(3001, "Token Expired"),
    TOKEN_INVALID(3002, "Token Invalid"),
    
    UNAUTHORIZED_ACCESS(4001, "Unauthorized access"),

    PRODUCT_NOT_FOUND(5001, "Product not found"),
    PRODUCT_ALREADY_EXISTS(5002, "Product already exists"),

    ORDER_NOT_FOUND(6001,"Order not found"),
    INSUFICIENT_STOCK(6002,"Not enough stock");
    
    private final int statusCode;
    private final String message;
}