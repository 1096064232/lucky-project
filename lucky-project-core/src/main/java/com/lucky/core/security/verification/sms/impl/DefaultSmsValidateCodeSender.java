package com.lucky.core.security.verification.sms.impl;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.security.verification.sms.SmsValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author ouyangfan
 * @Date 2019/3/221:31
 **/
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SimpleResponse send(String mobile, String code) throws ValidateCodeException {
        logger.info("向手机"+mobile+"发送短信验证码"+code);
        return new SimpleResponse("success");
    }
}
