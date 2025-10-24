package com.ticketsystem.exceptions.impl;

import com.ticketsystem.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PermissionForbiddenException extends BaseException {
    public PermissionForbiddenException(String message) {
        super(message);
        httpStatus = HttpStatus.FORBIDDEN;
    }
}
