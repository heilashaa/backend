package com.haapp.formicary.infrastructure.exception;

public class AuthException extends ServiceException{

    public AuthException(String code) {
        super(code);
    }
}
