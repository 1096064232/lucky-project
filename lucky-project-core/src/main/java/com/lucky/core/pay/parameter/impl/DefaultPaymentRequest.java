package com.lucky.core.pay.parameter.impl;

import com.lucky.core.pay.parameter.PaymentRequest;

public class DefaultPaymentRequest implements PaymentRequest {

    /**
     *  商户号
     */
    private String merchantNo;

    /**
     *  订单金额
     */
    private String orderAmount;

    /**
     *  订单标题
     */
    private  String orderTitle;

    /**
     *  订单详情
     */
    private String orderDetails;

    /**
     *  附加消息
     */
    private  String attachInfo;

    /**
     *   异步通知地址
     */
    private String notifyUrl;

    /**
     *  同步通知地址
     */
    private String returnUrl;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(String attachInfo) {
        this.attachInfo = attachInfo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
