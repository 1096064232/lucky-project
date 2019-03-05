package com.lucky.demo.browser.security.verification.sms;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.constant.ValidateCodeConstant;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.impl.AbstractValidateCodeProcessor;
import com.lucky.core.security.verification.sms.SmsValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author ouyangfan
 * @Date 2019/3/223:32
 **/
@Component(ValidateCodeConstant.SMS_CODE + "ValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LuckyProperties luckyProperties;

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsValidateCodeSender smsCodeSender;

    @Override
    public SimpleResponse send(ServletWebRequest request, ValidateCode validateCode) throws ValidateCodeException {

        logger.debug("这是demo的SmsValidateCodeProcessor");
        String mobile = null;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), luckyProperties.getSecurity().getParameter().getCode().getSmsMobileParameterName());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException(e);
        }
        return smsCodeSender.send(mobile, validateCode.getCode());
    }
}
