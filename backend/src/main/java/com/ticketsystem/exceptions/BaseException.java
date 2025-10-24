package com.ticketsystem.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{
    protected HttpStatus httpStatus;

    public BaseException(String message){
        super(message);
    }
}
