package com.lucky.demo.browser.handler;

import com.lucky.core.exception.ValidateCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DemoExceptionHandler {

    /**
     *  验证码异常处理器
     * @param exception
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidateCodeException.class)
    public Map<String,String> validateCodeExceptionHandler(ValidateCodeException exception){
        Map<String,String> map = new HashMap<>();
        map.put("message",exception.getMessage());
        return map;
    }

}
