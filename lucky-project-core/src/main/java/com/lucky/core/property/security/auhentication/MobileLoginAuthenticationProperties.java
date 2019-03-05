package com.lucky.core.property.security.auhentication;

/**
 *  手机号+短信登录的配置项
 */
public class MobileLoginAuthenticationProperties extends FormLoginAuthenticationProperties{

    public MobileLoginAuthenticationProperties() {
        setLoginProcessingUrl("/authentication/mobile");
    }
}
