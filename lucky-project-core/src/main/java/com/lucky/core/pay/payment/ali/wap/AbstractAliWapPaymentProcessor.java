package com.lucky.core.pay.payment.ali.wap;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.lucky.core.pay.parameter.PaymentRequest;
import com.lucky.core.pay.parameter.PaymentResponse;
import com.lucky.core.pay.parameter.impl.DefaultPaymentResponse;
import com.lucky.core.pay.payment.ali.AbstractAliPaymentProcessor;



/**
 * 支付宝手机网站支付抽象类
 */
public abstract class AbstractAliWapPaymentProcessor extends AbstractAliPaymentProcessor {

    /**
     *  支付宝手机网站支付的产品码
     */
    private static  final String PRODUCT_CODE = "QUICK_WAP_WAY";

    /**
     * 组装支付请求
     *
     * @param paymentRequest
     * @return
     */
    @Override
    protected AlipayTradeWapPayRequest getAlipayRequest(PaymentRequest paymentRequest) {

        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
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
     *  获取支付宝支付的产品码
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
    protected PaymentResponse payResponseAdpter(AlipayResponse alipayResponse) {

        AlipayTradeWapPayResponse alipayTradeWapPayResponse = (AlipayTradeWapPayResponse) alipayResponse;
        PaymentResponse paymentResponse = new DefaultPaymentResponse();
        ((DefaultPaymentResponse) paymentResponse).setBody(alipayTradeWapPayResponse.getBody());
        return paymentResponse;
    }

}
