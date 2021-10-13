package com.sipios.refactoring.exception;

public class PriceTooHighException extends RuntimeException {
    public PriceTooHighException(String s) {
        super(s);
    }
}
