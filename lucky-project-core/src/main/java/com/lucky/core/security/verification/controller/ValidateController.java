package com.lucky.core.security.verification.controller;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.common.util.CommonUtils;
import com.lucky.core.property.constant.ValidateCodeConstant;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.ValidateCodeProcessor;
import com.lucky.core.security.verification.ValidateCodeProcessorHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ouyangfan
 * @Date 2019/3/222:03
 **/
@RestController
public class ValidateController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(ValidateCodeConstant.CODE_URL+"/{type}")
    public void getValidateCode(@PathVariable(value = "type")String type, HttpServletRequest request, HttpServletResponse response){
        //获取验证码处理器
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
        ServletWebRequest servletWebRequest = new ServletWebRequest(request,response);
        //获取验证码
        SimpleResponse simpleResponse = validateCodeProcessor.create(servletWebRequest);
        logger.debug("获取验证码的结果是:{}",CommonUtils.toString(simpleResponse.getResponse()));
        //发送验证码
        SimpleResponse sendResponse = validateCodeProcessor.send(servletWebRequest, (ValidateCode) simpleResponse.getResponse());
        logger.debug("发送验证码的结果是:{}",CommonUtils.toString(sendResponse));
    }
}
