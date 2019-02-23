package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.RefundResponse;



public class DefaultRefundResponse  extends  DefaultPublicResponse implements RefundResponse {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  第三方支付号
     */
    private String paymentNo;

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
    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

}
