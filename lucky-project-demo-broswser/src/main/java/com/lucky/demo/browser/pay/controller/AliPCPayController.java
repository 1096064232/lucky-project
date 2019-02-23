package com.lucky.demo.browser.pay.controller;

import com.lucky.demo.browser.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试支付宝电脑网站支付
 */
@RestController
@RequestMapping("/pay/ali/pc")
public class AliPCPayController {

    @Autowired
    PayService aliPCPayServiceImpl;

    /**
     * 发起支付请求
     *
     * @param request
     * @param response
     */
    @PostMapping("/payment")
    public void payment(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.payment(new ServletWebRequest(request, response));
    }


    /**
     *  支付结果查询
     * @param request
     * @param response
     */
    @PostMapping("/payment/query")
    public void paymentQuery(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.paymentQurey(new ServletWebRequest(request, response));
    }

    /**
     *  退款请求
     * @param request
     * @param response
     */
    @PostMapping("/refund")
    public void refund(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.refund(new ServletWebRequest(request, response));
    }

    /**
     *  退款结果查询
     * @param request
     * @param response
     */
    @PostMapping("/refund/query")
    public void refundQuery(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.refundQuery(new ServletWebRequest(request, response));
    }

    /**
     * 异步通知
     *
     * @param request
     * @param response
     */
    @PostMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.notifyNote(new ServletWebRequest(request, response));
    }

    /**
     * 同步通知
     *
     * @param request
     * @param response
     */
    @GetMapping("/return")
    public void returnNote(HttpServletRequest request, HttpServletResponse response) {
        aliPCPayServiceImpl.returnNote(new ServletWebRequest(request, response));
    }


}
