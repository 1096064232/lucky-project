package com.lucky.core.pay.payment.weixin.parameter;

import com.lucky.core.pay.parameter.impl.DefaultAsynchronousResponse;

public class WeixinAsynchronousResponse extends DefaultAsynchronousResponse {

    private WeixinInnerAsynchronousResponse innerResponse;

    public WeixinAsynchronousResponse() {
        this.innerResponse = new WeixinInnerAsynchronousResponse();
    }

    public class WeixinInnerAsynchronousResponse extends WeixinPulicResponse {

    }

    public WeixinInnerAsynchronousResponse getInnerResponse() {
        return innerResponse;
    }
}
