package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.TradeClsoeResponse;

public class DefaultTradeClsoeResponse extends DefaultPublicResponse implements TradeClsoeResponse {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  第三方支付号
     */
    private String paymentNo;

    @Override
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
