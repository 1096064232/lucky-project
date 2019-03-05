package com.lucky.core.pay.payment.weixin.parameter;

import com.lucky.core.pay.parameter.impl.DefaultPaymentResponse;

public class WeixinPaymentResponse extends DefaultPaymentResponse {

    private WeixinInnerPaymentResponse innerResponse;

    public WeixinPaymentResponse() {
        this.innerResponse = new WeixinInnerPaymentResponse();
    }

    public class WeixinInnerPaymentResponse extends WeixinPulicResponse {

    }

    public WeixinInnerPaymentResponse getInnerResponse() {
        return innerResponse;
    }

}
