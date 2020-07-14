package com.haapp.formicary.infrastructure.exception;

public class NotFoundException extends ServiceException {

    public NotFoundException(String code) {
        super(code);
    }
}
