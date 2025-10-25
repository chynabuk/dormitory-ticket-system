package com.ticketsystem.exceptions;

import com.ticketsystem.exceptions.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> recordNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), e.httpStatus);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> nullPropertyException(DataIntegrityViolationException e){
        return new ResponseEntity<>(e.getMessage(), e.httpStatus);
    }

    @ExceptionHandler(ExpectationFailedException.class)
    public ResponseEntity<String> expectationFailedException(ExpectationFailedException e){
        return new ResponseEntity<>(e.getMessage(), e.httpStatus);
    }

    @ExceptionHandler(PasswordNotMatchingException.class)
    public ResponseEntity<String> passwordNotMatchingException(PasswordNotMatchingException e){
        return new ResponseEntity<>(e.getMessage(), e.httpStatus);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<String> resourceDuplicateException(ResourceDuplicateException e){
        return new ResponseEntity<>(e.getMessage(), e.httpStatus);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> iOException(IOException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
