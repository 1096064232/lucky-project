package com.lucky.demo.browser.pay.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public Map illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", exception.getMessage());
        return result;
    }
}
