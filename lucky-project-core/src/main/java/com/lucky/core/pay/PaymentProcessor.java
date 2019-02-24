package com.lucky.core.pay;

import com.lucky.core.exception.PayException;
import com.lucky.core.pay.parameter.*;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *  支付/退款 处理器
 */
public interface PaymentProcessor {

    /**
     * 发起支付请求
     * @param servletWebRequest
     * @param paymentRequest
     * @return
     */
    PaymentResponse launchPaymentProcessor(ServletWebRequest servletWebRequest, PaymentRequest paymentRequest) throws PayException;

    /**
     *  异步回调处理
     * @param servletWebRequest
     * @return
     */
    AsynchronousResponse callBacksHandler(ServletWebRequest servletWebRequest);

    /**
     *  支付结果查询
     * @param servletWebRequest
     * @param paymentResultQueryRequest
     * @return
     */
    PaymentResultQueryResponse paymentResultQuery(ServletWebRequest servletWebRequest, PaymentResultQueryRequest paymentResultQueryRequest) throws PayException;

    /**
     *  发起退款请求
     * @param servletWebRequest
     * @param refundRequest
     * @return
     */
    RefundResponse launchRefundProcessor(ServletWebRequest servletWebRequest,RefundRequest refundRequest);

    /**
     * 退款请求查询
     * @param servletWebRequest
     * @param refundResultQureyRequest
     * @return
     */
    RefundResultQureyResponse refundResultQuery(ServletWebRequest servletWebRequest,RefundResultQureyRequest refundResultQureyRequest);

    /**
     *  交易关闭
     *  用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     * @param servletWebRequest
     * @param tradeCloseRequest
     * @return
     */
    TradeClsoeResponse tradeClose(ServletWebRequest servletWebRequest,TradeCloseRequest tradeCloseRequest);
}
