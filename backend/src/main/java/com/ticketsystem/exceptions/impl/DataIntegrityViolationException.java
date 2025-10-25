package com.ticketsystem.exceptions.impl;

import com.ticketsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class DataIntegrityViolationException extends BaseException {
    public DataIntegrityViolationException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
