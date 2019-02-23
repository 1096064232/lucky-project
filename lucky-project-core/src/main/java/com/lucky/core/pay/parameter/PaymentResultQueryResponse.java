package com.lucky.core.pay.parameter;

import java.util.Map;

/**
 *  支付结果查询请求响应
 */
public interface PaymentResultQueryResponse  extends  PublicResponse{

    /**
     * 商户号
     *
     * @return
     */
     String getMerchantNo();

    /**
     * 第三方支付号
     *
     * @return
     */
     String getPaymentNo();

    /**
     * 交易状态
     *
     * @return
     */
     String getTradingStatus();

    /**
     * 订单金额
     *
     * @return
     */
     String getOrderAmount();

}
