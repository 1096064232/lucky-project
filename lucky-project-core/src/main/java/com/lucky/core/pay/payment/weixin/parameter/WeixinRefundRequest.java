package com.lucky.core.pay.payment.weixin.parameter;

import com.lucky.core.pay.parameter.impl.DefaultRefundRequest;

public class WeixinRefundRequest extends DefaultRefundRequest {

    /***
     *  清单金额
     */
    private String orderAmount;

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
