package com.lucky.core.pay.payment.weixin.parameter;


import com.lucky.core.pay.parameter.impl.DefaultPaymentResultQueryResponse;

public class WeixinPaymentResultQueryResponse extends DefaultPaymentResultQueryResponse {

    private WeixinInnerPaymentResultQueryResponse innerResponse;

    public WeixinPaymentResultQueryResponse() {
        this.innerResponse = new WeixinInnerPaymentResultQueryResponse();
    }

    public class WeixinInnerPaymentResultQueryResponse extends WeixinPulicResponse {

    }

    public WeixinInnerPaymentResultQueryResponse getInnerResponse() {
        return innerResponse;
    }

}
