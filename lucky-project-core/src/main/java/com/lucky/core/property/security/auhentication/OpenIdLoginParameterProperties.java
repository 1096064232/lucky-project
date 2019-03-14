package com.lucky.core.property.security.auhentication;

public class OpenIdLoginParameterProperties {

    /**
     * openid参数名
     */
    private String openIdParameterName="openId";

    /**
     *  providerid参数名
     */
    private String providerIdParameterName = "providerId";

    public String getOpenIdParameterName() {
        return openIdParameterName;
    }

    public void setOpenIdParameterName(String openIdParameterName) {
        this.openIdParameterName = openIdParameterName;
    }

    public String getProviderIdParameterName() {
        return providerIdParameterName;
    }

    public void setProviderIdParameterName(String providerIdParameterName) {
        this.providerIdParameterName = providerIdParameterName;
    }
}
