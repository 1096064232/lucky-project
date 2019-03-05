package com.lucky.core.property.security.auhentication;

/**
 * 认证配置项
 */
public class AuthenticationProperties {

    /**
     *  当需要认证时跳转的URL
     */
    private String unAuthenticationUrl="/unautentication";
    /**
     *  用户名加密码登录配置项
     */
    private FormLoginAuthenticationProperties formLogin = new FormLoginAuthenticationProperties();

    /**
     *  手机号+短信登录的配置项
     */
    private MobileLoginAuthenticationProperties mobileLogin = new MobileLoginAuthenticationProperties();

    public String getUnAuthenticationUrl() {
        return unAuthenticationUrl;
    }

    public void setUnAuthenticationUrl(String unAuthenticationUrl) {
        this.unAuthenticationUrl = unAuthenticationUrl;
    }

    public FormLoginAuthenticationProperties getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(FormLoginAuthenticationProperties formLogin) {
        this.formLogin = formLogin;
    }

    public MobileLoginAuthenticationProperties getMobileLogin() {
        return mobileLogin;
    }

    public void setMobileLogin(MobileLoginAuthenticationProperties mobileLogin) {
        this.mobileLogin = mobileLogin;
    }
}
