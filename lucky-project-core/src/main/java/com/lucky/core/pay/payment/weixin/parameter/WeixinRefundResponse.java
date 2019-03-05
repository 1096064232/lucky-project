package com.lucky.core.pay.payment.weixin.parameter;

import com.lucky.core.pay.parameter.impl.DefaultRefundResponse;

public class WeixinRefundResponse extends DefaultRefundResponse {

    private WeixinInnerRefundResponse innerResponse;

    /**
     *  商户退款编号
     */
    private String merchantRefundNo;

    /**
     *  微信退款编号
     */
    private String refundNo;

    public WeixinRefundResponse() {
        this.innerResponse = new WeixinInnerRefundResponse();
    }

    public class WeixinInnerRefundResponse extends WeixinPulicResponse{

    }

    public void setInnerResponse(WeixinInnerRefundResponse innerResponse) {
        this.innerResponse = innerResponse;
    }

    public String getMerchantRefundNo() {
        return merchantRefundNo;
    }

    public void setMerchantRefundNo(String merchantRefundNo) {
        this.merchantRefundNo = merchantRefundNo;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public WeixinInnerRefundResponse getInnerResponse() {
        return innerResponse;
    }
}
