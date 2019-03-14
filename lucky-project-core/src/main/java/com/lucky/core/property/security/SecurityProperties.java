package com.lucky.core.property.security;

import com.lucky.core.property.security.auhentication.AuthenticationProperties;
import com.lucky.core.property.security.verification.ValidateCodeProperties;

/**
 *  Security的安全配置项
 */
public class SecurityProperties {

    /**
     *  验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     *  参数名称配置
     */
    private ParameterProperties parameter = new ParameterProperties();

    /**
     *  登录认证的配置项
     */
    private AuthenticationProperties authentication = new AuthenticationProperties();


    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }

    public ParameterProperties getParameter() {
        return parameter;
    }

    public void setParameter(ParameterProperties parameter) {
        this.parameter = parameter;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
