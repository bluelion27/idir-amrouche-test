package com.sipios.refactoring.controller;

import com.sipios.refactoring.exception.ParameterException;
import com.sipios.refactoring.exception.PriceTooHighException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = PriceTooHighException.class)
    public ResponseEntity<String> gameParameterExceptionHandler(PriceTooHighException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ParameterException.class)
    public ResponseEntity<String> gameParameterExceptionHandler(ParameterException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
