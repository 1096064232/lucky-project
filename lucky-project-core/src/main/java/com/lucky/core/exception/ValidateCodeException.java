package com.lucky.core.exception;

/**
 * @Author ouyangfan
 * @Date 2019/3/219:32
 **/
public class ValidateCodeException extends RuntimeException {
    public ValidateCodeException() {
        super();
    }

    public ValidateCodeException(String message) {
        super(message);
    }

    public ValidateCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateCodeException(Throwable cause) {
        super(cause);
    }

    protected ValidateCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
