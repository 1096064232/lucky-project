package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.PaymentResultQueryResponse;

import java.util.HashMap;
import java.util.Map;

public class DefaultPaymentResultQueryResponse  extends  DefaultPublicResponse implements PaymentResultQueryResponse {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  第三方支付号
     */
    private String paymentNo;

    /**
     *  交易状态
     */
    private String tradingStatus;

    /**
     *  订单金额
     */
    private String orderAmount;

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

    @Override
    public String getTradingStatus() {
        return tradingStatus;
    }

    public void setTradingStatus(String tradingStatus) {
        this.tradingStatus = tradingStatus;
    }

    @Override
    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

}
