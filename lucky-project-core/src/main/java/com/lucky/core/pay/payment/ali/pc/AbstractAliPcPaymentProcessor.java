package com.lucky.core.pay.payment.ali.pc;

import com.alipay.api.AlipayObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.lucky.common.util.CommonUtils;
import com.lucky.common.util.OrderUtils;
import com.lucky.core.pay.parameter.PaymentRequest;
import com.lucky.core.pay.parameter.PaymentResponse;
import com.lucky.core.pay.parameter.impl.DefaultPaymentResponse;
import com.lucky.core.pay.payment.ali.AbstractAliPaymentProcessor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 支付宝电脑网站支付抽象类
 */
public abstract class AbstractAliPcPaymentProcessor extends AbstractAliPaymentProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 对支付宝电脑网站支付请求的必须需要的参数进行校验 需要进行校验的参数如下：
     * <p>
     * out_trade_no  商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     * <p>
     * product_code  销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
     * <p>
     * total_amount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]  最大长度11
     * <p>
     * subject 订单标题 最大长度256
     *
     * @param alipayRequest
     */
    @Override
    protected void paymentRequestParametersValidate(AlipayRequest alipayRequest) {

        Assert.notNull(alipayRequest, "AlipayRequest为空，无法发起支付宝电脑网站支付请求");
        AlipayObject alipayObject = alipayRequest.getBizModel();
        if (isSupportAlipayObject(alipayObject.getClass())) {
            AlipayTradePagePayModel alipayTradePagePayModel = (AlipayTradePagePayModel) alipayObject;
            // 商户订单号
            String out_trade_no = alipayTradePagePayModel.getOutTradeNo();
            //产品码
            String product_code = alipayTradePagePayModel.getProductCode();
            //订单总金额
            String total_amount = alipayTradePagePayModel.getTotalAmount();
            //订单标题
            String subject = alipayTradePagePayModel.getSubject();
            Assert.notNull(subject, "订单标题为空，无法发起支付宝电脑网站支付请求");
            //商户订单号是否为空
            if (StringUtils.isBlank(out_trade_no)) {
                throw new IllegalArgumentException("商户订单号为空，无法发起支付宝电脑网站支付请求");
            }
            if (out_trade_no.length() > 64) {
                throw new IllegalArgumentException("商户订单号长度超过了64位，无法发起支付宝电脑网站支付请求");
            }
            if (!OrderUtils.validateOrderNo(out_trade_no)) {
                throw new IllegalArgumentException("商户订单号包含字母、数字、下划线之外的字符，无法发起支付宝电脑网站支付请求");
            }
            if (!StringUtils.equals(product_code, "FAST_INSTANT_TRADE_PAY")) {
                throw new IllegalArgumentException("传入的支付宝电脑网站支付的产品码不是FAST_INSTANT_TRADE_PAY，无法发起支付宝电脑网站支付请求");
            }
            try {
                double orderAmount = Double.valueOf(total_amount);
                if (orderAmount < 0.01 || orderAmount > 100000000) {
                    throw new IllegalArgumentException("传入的订单金额无法转化为double类型，无法发起支付宝电脑网站支付请求");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("传入的订单金额无法转化为double类型，无法发起支付宝电脑网站支付请求");
            }
        }
    }


    @Override
    protected boolean isSupportAlipayObject(Class cls) {
        return AlipayTradePagePayModel.class.isAssignableFrom(cls);
    }

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
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);
        // 设置异步通知地址
        request.setNotifyUrl(paymentRequest.getNotifyUrl());
        // 设置同步地址
        request.setReturnUrl(paymentRequest.getReturnUrl());
        return request;
    }

    /**
     * 将支付宝支付返回的结果转化为自定义的支付请求返回结果
     *
     * @param alipayResponse
     * @return
     */
    @Override
    protected PaymentResponse payResponseAdpter(AlipayResponse alipayResponse) {

        logger.info("支付宝支付结果为:{}", CommonUtils.toString(alipayResponse));
        AlipayTradePagePayResponse alipayTradePagePayResponse = (AlipayTradePagePayResponse) alipayResponse;
        PaymentResponse paymentResponse = new DefaultPaymentResponse();
//        ((DefaultPaymentResponse) paymentResponse).setGatewayCode(alipayTradePagePayResponse.getCode());
//        ((DefaultPaymentResponse) paymentResponse).setGatewayMessage(alipayTradePagePayResponse.getMsg());
//        ((DefaultPaymentResponse) paymentResponse).setBusinessCode(alipayTradePagePayResponse.getSubCode());
//        ((DefaultPaymentResponse) paymentResponse).setBusinessMessage(alipayTradePagePayResponse.getSubMsg());
//        ((DefaultPaymentResponse) paymentResponse).setSign((String) alipayTradePagePayResponse.getParams().get("sign"));
//        ((DefaultPaymentResponse) paymentResponse).setParameters(alipayTradePagePayResponse.getParams());
        ((DefaultPaymentResponse) paymentResponse).setBody(alipayTradePagePayResponse.getBody());
        return paymentResponse;
    }

}
