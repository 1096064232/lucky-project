package com.lucky.core.pay.parameter;


/**
 *  发起支付请求所需要的参数
 */
public interface PaymentRequest {

    /**
     *  获取商户定单编号
     * @return
     */
    String getMerchantNo();

    /**
     *  获取定单金额
     * @return
     */
    String getOrderAmount();

    /**
     *  获取定单标题
     * @return
     */
    String getOrderTitle();

    /**
     * 获取定单详情
     * @return
     */
    String getOrderDetails();

    /**
     *  获取附加的消息
     * @return
     */
    String getAttachInfo();

    /**
     *  获取异步回调url
     * @return
     */
    String getNotifyUrl();

    /**
     *  获取同步回调url
     * @return
     */
    String getReturnUrl();
}
