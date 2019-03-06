package com.lucky.core.exception;

import org.springframework.security.core.AuthenticationException;

public class PhoneNumberNotFoundException extends AuthenticationException {
    public PhoneNumberNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public PhoneNumberNotFoundException(String msg) {
        super(msg);
    }
}
