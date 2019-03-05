package com.lucky.core.property.security;

import com.lucky.core.property.security.auhentication.FormLoginParameterProperties;
import com.lucky.core.property.security.auhentication.MobileLoginParameterProperties;
import com.lucky.core.property.security.verification.ValidateCodeParameterProperties;
import sun.dc.pr.PRError;

/**
 *  参数名称配置项
 * @Author ouyangfan
 * @Date 2019/3/220:15
 **/
public class ParameterProperties {

    /**
     *  验证码的参数名称配置
     */
    private ValidateCodeParameterProperties code = new ValidateCodeParameterProperties();

    /**
     *  手机号+短信验证码登录参数名称配置项
     */
    private MobileLoginParameterProperties mobileLogin = new MobileLoginParameterProperties();

    /**
     *  用户名+密码登录参数名称配置项
     */
    private FormLoginParameterProperties formLogin = new FormLoginParameterProperties();

    public FormLoginParameterProperties getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(FormLoginParameterProperties formLogin) {
        this.formLogin = formLogin;
    }

    public MobileLoginParameterProperties getMobileLogin() {
        return mobileLogin;
    }

    public void setMobileLogin(MobileLoginParameterProperties mobileLogin) {
        this.mobileLogin = mobileLogin;
    }

    public ValidateCodeParameterProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeParameterProperties code) {
        this.code = code;
    }
}
