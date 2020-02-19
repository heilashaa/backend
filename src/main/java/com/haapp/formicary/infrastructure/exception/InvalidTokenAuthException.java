package com.haapp.formicary.infrastructure.exception;

public class InvalidTokenAuthException extends ServiceException {

    public InvalidTokenAuthException(String code) {
        super(code);
    }
}
