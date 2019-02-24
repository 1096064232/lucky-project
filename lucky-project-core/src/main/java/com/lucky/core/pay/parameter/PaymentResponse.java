package com.lucky.core.pay.parameter;


/**
 *  支付请求发送完成获取响应
 */
public interface PaymentResponse extends  PublicResponse {

    /**
     *  返回数据
     * @return
     */
     Object getBody();

}
