package com.ticketsystem.exceptions.impl;

import com.ticketsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ExpectationFailedException extends BaseException {
    public ExpectationFailedException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}