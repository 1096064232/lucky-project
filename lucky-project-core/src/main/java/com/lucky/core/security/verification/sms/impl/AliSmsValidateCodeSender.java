package com.lucky.core.security.verification.sms.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.lucky.common.pojo.SimpleResponse;
import com.lucky.common.util.CommonUtils;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.verification.AliSmsCodeProperties;
import com.lucky.core.security.verification.sms.SmsValidateCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里云的短信服务
 *
 * @Author ouyangfan
 * @Date 2019/3/220:34
 **/
public class AliSmsValidateCodeSender implements SmsValidateCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IAcsClient acsClient;

    @Autowired
    LuckyProperties luckyProperties;

    @Override
    public SimpleResponse send(String mobile, String code) throws ValidateCodeException {
        AliSmsCodeProperties aliSmsCodeProperties = luckyProperties.getSecurity().getCode().getSms().getAliSmsCode();
        //组装参数请求
        CommonRequest request = new CommonRequest();
        request.setDomain(aliSmsCodeProperties.getProductDomain());
        request.setVersion("2017-05-25");
        // 系统规定参数。取值：SendSms。
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", aliSmsCodeProperties.getRegionId());
        //手机号 国内短信：11位手机号码，例如15951955195。
        //国际/港澳台消息：国际区号+号码，例如85200000000。
        request.putQueryParameter("PhoneNumbers", mobile);
        //短信签名名称
        request.putQueryParameter("SignName", aliSmsCodeProperties.getSignName());
        //短信模板ID
        request.putQueryParameter("TemplateCode", aliSmsCodeProperties.getTemplateCode());
        //短信模板变量对应的实际值，JSON格式 {"code":"1111"}
        request.putQueryParameter("TemplateParam", "{\" " + aliSmsCodeProperties.getTemplateParamName() + "\":" + code + "}");
        try {
            logger.debug("当前的IAcsClient对象是:{}", CommonUtils.toString(acsClient));
            return new SimpleResponse(acsClient.getCommonResponse(request));
        } catch (ClientException e) {
            throw new ValidateCodeException(e);
        }
    }
}
