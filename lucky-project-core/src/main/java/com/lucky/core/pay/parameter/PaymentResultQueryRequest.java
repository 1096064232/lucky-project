package com.lucky.core.pay.parameter;


/**
 *  支付结果查询请求
 */
public interface PaymentResultQueryRequest {


    /**
     *  商户号
     * @return
     */
     String getMerchantNo();


    /**
     *  第三方支付号
     * @return
     */
    String getPaymentNo() ;

}
