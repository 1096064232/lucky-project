package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.RefundResultQureyResponse;

public class DefaultRefundResultQureyResponse extends DefaultPublicResponse implements RefundResultQureyResponse {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  第三方支付号
     */
    private String paymentNo;

    /**
     *  退款编号
     */
    private String refundNo;

    /**
     *  订单金额
     */
    private String orderAmount;


    /**
     *  退款金额
     */
    private String refundAmount;

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
    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    @Override
    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
}
