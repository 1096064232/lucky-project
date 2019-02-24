package com.lucky.core.pay.payment.ali.pc;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.lucky.core.pay.parameter.PaymentRequest;
import com.lucky.core.pay.parameter.PaymentResponse;
import com.lucky.core.pay.parameter.impl.DefaultPaymentResponse;
import com.lucky.core.pay.payment.ali.AbstractAliPaymentProcessor;

/**
 * 支付宝电脑网站支付抽象类
 */
public abstract class AbstractAliPcPaymentProcessor extends AbstractAliPaymentProcessor {

    /**
     *  支付宝电脑网站支付的产品码
     */
    private static  final String PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";


    /**
     * 组装支付请求
     *
     * @param paymentRequest
     * @return
     */
    @Override
    protected AlipayTradePagePayRequest getAlipayRequest(PaymentRequest paymentRequest) {

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 封装请求支付信息
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        // 联合支付号
        model.setOutTradeNo(paymentRequest.getMerchantNo());
        // 支付金额
        model.setTotalAmount(paymentRequest.getOrderAmount());
        //订单名称
        model.setSubject(paymentRequest.getOrderTitle());
        //商品描述，可空
        model.setBody(paymentRequest.getOrderDetails());
        //回传参数
        model.setPassbackParams(paymentRequest.getAttachInfo());
        //支付超时时间
//		model.setTimeExpire("dsadsa");
        //销售产品码 必填
        model.setProductCode(getProductCode());
        request.setBizModel(model);
        // 设置异步通知地址
        request.setNotifyUrl(paymentRequest.getNotifyUrl());
        // 设置同步地址
        request.setReturnUrl(paymentRequest.getReturnUrl());
        return request;
    }

    /**
     *  获取支付宝支付类型的产品码
     * @return
     */
    @Override
    protected String getProductCode() {
        return PRODUCT_CODE;
    }

    /**
     * 将支付宝支付返回的结果转化为自定义的支付请求返回结果
     *
     * @param alipayResponse
     * @return
     */
    @Override
    protected PaymentResponse payResponseAdpter(AlipayResponse alipayResponse) {
        AlipayTradePagePayResponse alipayTradePagePayResponse = (AlipayTradePagePayResponse) alipayResponse;
        PaymentResponse paymentResponse = new DefaultPaymentResponse();
        ((DefaultPaymentResponse) paymentResponse).setBody(alipayTradePagePayResponse.getBody());
        return paymentResponse;
    }


}
