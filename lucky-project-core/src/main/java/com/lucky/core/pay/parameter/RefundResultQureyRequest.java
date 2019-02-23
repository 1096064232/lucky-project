package com.lucky.core.pay.parameter;

/**
 *  退款结果查询
 */
public interface RefundResultQureyRequest {

    /**
     *  商户号
     * @return
     */
     String getMerchantNo() ;

    /**
     *  第三方支付号
     * @return
     */
     String getPaymentNo();

    /**
     *   退款编号
     * @return
     */
     String getRefundNo();
}
