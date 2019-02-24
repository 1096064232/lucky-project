package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.AsynchronousResponse;
import com.lucky.core.pay.parameter.PaymentResultQueryResponse;

import java.util.HashMap;
import java.util.Map;

public class DefaultAsynchronousResponse extends DefaultPaymentResultQueryResponse implements AsynchronousResponse {
    /**
     *  获取附加的消息
     * @return
     */
   private String attachInfo;

    /**
     *  异步签名结果
     */
   private boolean isSignValidate = false;

    @Override
    public boolean isSignValidate() {
        return isSignValidate;
    }

    public void setSignValidate(boolean signValidate) {
        isSignValidate = signValidate;
    }

    @Override
    public String getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(String attachInfo) {
        this.attachInfo = attachInfo;
    }


}

