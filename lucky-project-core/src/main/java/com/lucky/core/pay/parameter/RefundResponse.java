package com.lucky.core.pay.parameter;

/**
 *  发起退款请求后的响应
 */
public interface RefundResponse  extends  PublicResponse {

    /**
     *  商户号
     * @return
     */
     String getMerchantNo() ;

    /**
     *  第三方支付编号
     * @return
     */
     String getPaymentNo() ;

    /**
     *  退款总金额
     * @return
     */
     String getRefundAmount();

}
