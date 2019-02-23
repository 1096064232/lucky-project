package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.PublicResponse;

import java.util.HashMap;
import java.util.Map;

public class DefaultPublicResponse implements PublicResponse {

    /**
     *  网关返回码
     */
    private String gatewayCode;

    /**
     *  网关返回信息
     */
    private String gatewayMessage;

    /**
     *  业务返回码
     */
    private String businessCode;

    /**
     *  业务返回信息
     */
    private String businessMessage;

    /**
     *  签名
     */
    private String sign;


    /**
     *  参数集合
     */
    private Map<String, String> parameters = new HashMap<>();



    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }


    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }

    @Override
    public String getGatewayMessage() {
        return gatewayMessage;
    }

    public void setGatewayMessage(String gatewayMessage) {
        this.gatewayMessage = gatewayMessage;
    }

    @Override
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public String getBusinessMessage() {
        return businessMessage;
    }

    public void setBusinessMessage(String businessMessage) {
        this.businessMessage = businessMessage;
    }
}
