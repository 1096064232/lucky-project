package com.lucky.core.pay.parameter;

/**
 * 退款请求
 */
public interface RefundRequest extends PaymentResultQueryRequest {

    /**
     *  退款编号
     */
    String getRefundNo();

    /**
     * 退款金额
     *
     * @return
     */
    String getRefundAmount();

    /**
     *  退款原因
     * @return
     */
    String getRefundReason();
}
