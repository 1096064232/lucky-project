package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.PaymentResponse;


public class DefaultPaymentResponse  extends  DefaultPublicResponse implements PaymentResponse {


    private Object body;


    @Override
    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}