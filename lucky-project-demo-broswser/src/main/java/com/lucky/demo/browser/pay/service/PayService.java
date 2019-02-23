package com.lucky.demo.browser.pay.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *  支付电脑网站支付测试
 */
public interface PayService {

    /**
     *  发起支付
     * @param request
     */
    void payment(ServletWebRequest request);

    /**
     *  支付结果查询
     * @param request
     */
    void paymentQurey(ServletWebRequest request);

    /**
     *  退款请求
     * @param request
     */
    void refund(ServletWebRequest request);

    /**
     *  退款结果查询
     * @param request
     */
    void refundQuery(ServletWebRequest request);

    /**
     * 异步通知
     * @param servletWebRequest
     */
    void notifyNote(ServletWebRequest servletWebRequest);

    /**
     * 同步通知
     * @param servletWebRequest
     */
    void returnNote(ServletWebRequest servletWebRequest);
}
