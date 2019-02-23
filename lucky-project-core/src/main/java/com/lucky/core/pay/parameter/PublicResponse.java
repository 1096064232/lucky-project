package com.lucky.core.pay.parameter;

import java.util.Map;

/**
 * 公共响应参数
 */
public interface PublicResponse {

    /**
     * 网关返回码
     */
    String getGatewayCode();

    /**
     * 网关返回信息
     */
    String getGatewayMessage();

    /**
     * 业务返回码
     */
    String getBusinessCode();

    /**
     * 业务返回信息
     */
    String getBusinessMessage();

    /**
     * 返回结果签名
     *
     * @return
     */
    String getSign();

    /**
     *  参数集合
     */
    Map<String, String> getParameters();

}
