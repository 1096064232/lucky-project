package com.lucky.core.property.security.auhentication;

import com.lucky.core.security.verification.ValidateCodeTypeEnum;

public class MobileLoginParameterProperties {

    /**
     *  手机号的参数名
     */
    private String mobileParameterName = "mobile";



    public String getMobileParameterName() {
        return mobileParameterName;
    }

    public void setMobileParameterName(String mobileParameterName) {
        this.mobileParameterName = mobileParameterName;
    }

}
