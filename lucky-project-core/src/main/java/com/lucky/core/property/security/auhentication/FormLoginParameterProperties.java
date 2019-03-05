package com.lucky.core.property.security.auhentication;

import com.lucky.core.security.verification.ValidateCodeTypeEnum;

public class FormLoginParameterProperties {

    /**
     *  用户名的参数名称
     */
    private String usernameParameterName="username";

    /**
     *  密码的参数名称
     */
    private String passwordParameterName = "password";


    public String getUsernameParameterName() {
        return usernameParameterName;
    }

    public void setUsernameParameterName(String usernameParameterName) {
        this.usernameParameterName = usernameParameterName;
    }

    public String getPasswordParameterName() {
        return passwordParameterName;
    }

    public void setPasswordParameterName(String passwordParameterName) {
        this.passwordParameterName = passwordParameterName;
    }

}
