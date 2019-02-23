package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.PaymentResultQueryRequest;

public class DefaultPaymentResultQueryRequest implements PaymentResultQueryRequest {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  第三方支付号
     */
    private String paymentNo;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
