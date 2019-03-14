package com.lucky.demo.app.security.verification.sms;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.security.verification.sms.impl.AliSmsValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author ouyangfan
 * @Date 2019/3/223:10
 **/
//@Component
public class DemoAliSmsValidateSender extends AliSmsValidateCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SimpleResponse send(String mobile, String code) throws ValidateCodeException {

        logger.debug("这是demo的阿里云短信发送器");
        return super.send(mobile, code);
    }
}
