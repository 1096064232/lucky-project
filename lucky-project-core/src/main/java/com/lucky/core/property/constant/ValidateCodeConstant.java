package com.lucky.core.property.constant;

/**
 *  验证码常量
 * @Author ouyangfan
 * @Date 2019/3/219:30
 **/
public interface ValidateCodeConstant {

    /**
     *  短信验证码
     */
    String SMS_CODE = "sms";

    /**
     *  图形验证码
     */
    String IMAGE_CODE = "image";

    /**
     *  获取验证码的URL前缀
     */
    String CODE_URL = "/code";
}
