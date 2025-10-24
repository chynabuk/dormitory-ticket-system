package com.ticketsystem.exceptions.impl;

public class ResourceDuplicateException extends ExpectationFailedException {
    public ResourceDuplicateException(String message) {
        super(message);
    }
}
