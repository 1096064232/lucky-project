package com.lucky.core.property.security.auhentication;

/**
 *  用户名+密码的配值项
 */
public class FormLoginAuthenticationProperties  {

    /**
     *  登录处理的url
     */
    private String loginProcessingUrl = "/authentication/form";

    /**
     *  请求方式
     */
    private String httpMethod = "POST";


    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
