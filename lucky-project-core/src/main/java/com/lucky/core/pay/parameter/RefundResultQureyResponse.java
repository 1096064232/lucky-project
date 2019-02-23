package com.lucky.core.pay.parameter;

/**
 *  退款结果查询响应
 */
public interface RefundResultQureyResponse extends PublicResponse {

    /**
     *  商户编号
     * @return
     */
     String getMerchantNo() ;

    /**
     *  第三方支付编号
     * @return
     */
     String getPaymentNo() ;

    /**
     *  退款编号
     * @return
     */
     String getRefundNo() ;

    /**
     *  订单金额
     * @return
     */
    String getOrderAmount() ;

    /**
     *  退款金额
     * @return
     */
     String getRefundAmount() ;

}
