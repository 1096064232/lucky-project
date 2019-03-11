package com.lucky.core.exception;

public class SocialException extends RuntimeException {

    public SocialException() {
        super();
    }

    public SocialException(String message) {
        super(message);
    }

    public SocialException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocialException(Throwable cause) {
        super(cause);
    }

    protected SocialException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
