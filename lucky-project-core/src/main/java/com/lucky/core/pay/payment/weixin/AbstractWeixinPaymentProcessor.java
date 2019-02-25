package com.lucky.core.pay.payment.weixin;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.lucky.core.exception.PayException;
import com.lucky.core.pay.PaymentProcessor;
import com.lucky.core.pay.parameter.*;
import com.lucky.core.pay.payment.weixin.parameter.WeixinAsynchronousResponse;
import com.lucky.core.pay.payment.weixin.parameter.WeixinPaymentResponse;
import com.lucky.core.pay.payment.weixin.parameter.WeixinPaymentResultQueryResponse;
import com.lucky.core.property.LuckyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * 微信支付
 */
public abstract class AbstractWeixinPaymentProcessor implements PaymentProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WXPay wxPay;

    @Autowired
    LuckyProperties luckyProperties;

    /**
     * 发起微信支付请求
     * 除付款码支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
     * 返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。
     *
     * @param servletWebRequest
     * @param paymentRequest
     * @return
     * @throws PayException
     */
    @Override
    public PaymentResponse launchPaymentProcessor(ServletWebRequest servletWebRequest, PaymentRequest paymentRequest) throws PayException {
        Map<String, String> data = getWeixnPaymentRequest(paymentRequest);
        try {
            return payResponseAdpter(wxPay.unifiedOrder(data));
        } catch (Exception e) {
            throw new PayException(e);
        }
    }

    /**
     * 微信支付异步回调
     *
     * @param servletWebRequest
     * @return
     */
    @Override
    public AsynchronousResponse callBacksHandler(ServletWebRequest servletWebRequest) {
        AsynchronousResponse asynchronousResponse = new WeixinAsynchronousResponse();
        Map<String, String> result = getResult(servletWebRequest.getRequest());
        //返回的所有参数
        ((WeixinAsynchronousResponse) asynchronousResponse).setParameters(result);
        //网关码
        ((WeixinAsynchronousResponse) asynchronousResponse).setGatewayCode(result.get("return_code"));
        //网关信息
        ((WeixinAsynchronousResponse) asynchronousResponse).setGatewayMessage(result.get("return_msg"));
        //业务码
        ((WeixinAsynchronousResponse) asynchronousResponse).setGatewayMessage(result.get("result_code"));
        ((WeixinAsynchronousResponse) asynchronousResponse).setSign((String) result.get("sign"));
        ((WeixinAsynchronousResponse) asynchronousResponse).setMerchantNo((String) result.get("out_trade_no"));
        ((WeixinAsynchronousResponse) asynchronousResponse).setPaymentNo((String) result.get("transaction_id"));
        ((WeixinAsynchronousResponse) asynchronousResponse).setOrderAmount((String) result.get("total_fee"));
        ((WeixinAsynchronousResponse) asynchronousResponse).setSignValidate(signVerified(result));
        ((WeixinAsynchronousResponse) asynchronousResponse).setAttachInfo((String) result.get("attach"));
        WeixinAsynchronousResponse.WeixinInnerAsynchronousResponse innerResponse =   ((WeixinAsynchronousResponse) asynchronousResponse).getInnerResponse();
        innerResponse.setErrCode(result.get("err_code"));
        innerResponse.setErrCodeDes(result.get("err_code_des"));
        return asynchronousResponse;
    }

    /**
     * 校验异步通知签名
     *
     * @param params
     * @return
     */
    protected boolean signVerified(Map<String, String> params) {
        try {
            return   WXPayUtil.isSignatureValid(params,luckyProperties.getPay().getWeixin().getKey(),luckyProperties.getPay().getWeixin().getSignType());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  获取微信支付异步通知的参数集合
     * @param request
     * @return
     */
    protected Map<String, String> getResult(HttpServletRequest request) {

        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            return   WXPayUtil.xmlToMap(new String(outSteam.toByteArray(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    /**
     * 微信支付结果查询
     *
     * @param servletWebRequest
     * @param paymentResultQueryRequest
     * @return
     * @throws PayException
     */
    @Override
    public PaymentResultQueryResponse paymentResultQuery(ServletWebRequest servletWebRequest, PaymentResultQueryRequest paymentResultQueryRequest) throws PayException {
        Map<String, String> data = getWeixnPaymentTradeQueryRequest(paymentResultQueryRequest);
        try {
            return paymentResultQueryResponseAdpter(wxPay.orderQuery(data));
        } catch (Exception e) {
            throw new PayException(e);
        }
    }


    /**
     * 组装微信支付请求参数
     *
     * @param paymentRequest
     * @return
     */
    protected abstract Map<String, String> getWeixnPaymentRequest(PaymentRequest paymentRequest);

    /**
     * 组装微信下单后返回的参数
     *
     * @param map
     * @return
     */
    protected PaymentResponse payResponseAdpter(Map<String, String> map) {
        PaymentResponse paymentResponse = new WeixinPaymentResponse();
        for (String key : map.keySet()) {
            logger.info("微信统一下单返回的参数key is :{},value is :{}", key, map.get(key));
        }
        //网关码
        ((WeixinPaymentResponse) paymentResponse).setGatewayCode(map.get("return_code"));
        //网关信息
        ((WeixinPaymentResponse) paymentResponse).setGatewayMessage(map.get("return_msg"));
        //业务码
        ((WeixinPaymentResponse) paymentResponse).setGatewayMessage(map.get("result_code"));
        //请求返回的Map集合
        ((WeixinPaymentResponse) paymentResponse).setBody(map);
        //获取内部类
        WeixinPaymentResponse.WeixinInnerPaymentResponse innerRsponse = ((WeixinPaymentResponse) paymentResponse).getInnerResponse();
        innerRsponse.setErrCode(map.get("err_code"));
        innerRsponse.setErrCodeDes(map.get("err_code_des"));
        return paymentResponse;
    }


    /**
     * 组装微信支付订单查询请求参数
     *
     * @param paymentResultQueryRequest
     * @return
     */
    protected Map<String, String> getWeixnPaymentTradeQueryRequest(PaymentResultQueryRequest paymentResultQueryRequest) {
        Map<String, String> data = new HashMap<>();
        //支付编号
        data.put("transaction_id ", paymentResultQueryRequest.getPaymentNo());
        //商户号
        data.put("out_trade_no  ", paymentResultQueryRequest.getMerchantNo());
        return data;
    }

    /**
     * 组装微信支付订单查询响应结果
     *
     * @param map
     * @return
     */
    protected PaymentResultQueryResponse paymentResultQueryResponseAdpter(Map<String, String> map) {
        PaymentResultQueryResponse paymentResultQueryResponse = new WeixinPaymentResultQueryResponse();
        for (String key : map.keySet()) {
            logger.info("微信统支付订单查询响应结果参数key is :{},value is :{}", key, map.get(key));
        }
        //返回的所有参数
        ((WeixinPaymentResultQueryResponse) paymentResultQueryResponse).setParameters(map);
        //网关码
        ((WeixinPaymentResultQueryResponse) paymentResultQueryResponse).setGatewayCode(map.get("return_code"));
        //网关信息
        ((WeixinPaymentResultQueryResponse) paymentResultQueryResponse).setGatewayMessage(map.get("return_msg"));
        //业务码
        ((WeixinPaymentResultQueryResponse) paymentResultQueryResponse).setGatewayMessage(map.get("result_code"));
        WeixinPaymentResultQueryResponse.WeixinInnerPaymentResultQueryResponse innerResponse = ((WeixinPaymentResultQueryResponse) paymentResultQueryResponse).getInnerResponse();
        innerResponse.setErrCode(map.get("err_code"));
        innerResponse.setErrCodeDes(map.get("err_code_des"));
        return paymentResultQueryResponse;
    }


    /**
     * 获取微信支付的交易类型
     *
     * @return
     */
    protected abstract String getTradeType();


}
