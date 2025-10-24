package com.ticketsystem.exceptions.impl;

import com.ticketsystem.exceptions.BaseException;

public class PasswordNotMatchingException extends ExpectationFailedException {

    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
