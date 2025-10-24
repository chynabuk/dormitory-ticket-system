package com.ticketsystem.exceptions.impl;

import com.ticketsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}