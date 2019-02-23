package com.lucky.core.pay.parameter;

import java.util.Map;

/**
 *  支付请求发送完成获取的相应
 */
public interface PaymentResponse extends  PublicResponse {

    /**
     *
     * @return
     */
     Object getBody();

}
