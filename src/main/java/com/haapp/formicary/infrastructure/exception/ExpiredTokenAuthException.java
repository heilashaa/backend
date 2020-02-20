package com.haapp.formicary.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenAuthException extends AuthenticationException {
    public ExpiredTokenAuthException(String msg) {
        super(msg);
    }
}
