package com.lucky.demo.browser.security.verification.sms;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.security.verification.sms.SmsValidateCodeSender;
import com.lucky.core.security.verification.sms.impl.DefaultSmsValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @Author ouyangfan
 * @Date 2019/3/223:07
 **/
//@Component
public class DemoSmsValidateSender extends DefaultSmsValidateCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SimpleResponse send(String mobile, String code) throws ValidateCodeException {
        logger.debug("这是demo的短信发送器");
      return   super.send(mobile,code);
    }
}
