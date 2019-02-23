package com.lucky.core.pay.parameter;

/**
 * 第三放支付发起的异步通知的处理响应
 */
public interface AsynchronousResponse extends PaymentResultQueryResponse {

    /**
     *  获取附加的消息
     * @return
     */
    String getAttachInfo();

    /**
     * 异步通知签名校验结果
     *
     * @return
     */
    boolean isSignValidate();

}