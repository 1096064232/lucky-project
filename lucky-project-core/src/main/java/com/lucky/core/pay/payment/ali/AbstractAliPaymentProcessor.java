package com.lucky.core.pay.payment.ali;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lucky.common.util.CommonUtils;
import com.lucky.common.util.HttpRequest;
import com.lucky.common.util.JsonUtil;
import com.lucky.common.util.MapUtils;
import com.lucky.core.exception.PayException;
import com.lucky.core.pay.PaymentProcessor;
import com.lucky.core.pay.parameter.*;
import com.lucky.core.pay.parameter.impl.*;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.ali.AliPayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 无论是支付宝电脑网站支付还是支付宝手机网站支付，支付流程及其所需要的参数基本相同,
 * 因此在此实现一个抽象类，将支付宝电脑网站支付和支付宝手机网站支付相同的流程抽取出来
 */
public abstract class AbstractAliPaymentProcessor implements PaymentProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LuckyProperties luckyProperties;

    @Autowired
    AlipayClient alipayClient;


    /**
     * 发起支付宝支付请求
     *
     * @param servletWebRequest
     * @param paymentRequest
     * @return
     * @throws AlipayApiException
     */
    @Override
    public PaymentResponse launchPaymentProcessor(ServletWebRequest servletWebRequest, PaymentRequest paymentRequest) throws PayException {
        //获取支付宝支付请求所需要的参数
        AlipayRequest request = getAlipayRequest(paymentRequest);
        //发起支付请求之前做参数校验，支付宝电脑网站支付请求的必要参数是符合支付宝规则
        paymentRequestParametersValidate(request);
        //返回支付请求结果
        try {
            return payResponseAdpter(alipayClient.pageExecute(request));
        } catch (AlipayApiException e) {
            throw new PayException(e);
        }
    }

    /**
     *  支付宝支付请求异步通知处理逻辑
     *
     * @param servletWebRequest
     * @return
     */
    @Override
    public AsynchronousResponse callBacksHandler(ServletWebRequest servletWebRequest) {
        //获取所有返回的参数
        Map requestParams = servletWebRequest.getParameterMap();
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        AsynchronousResponse response = new DefaultAsynchronousResponse();

        ((DefaultAsynchronousResponse) response).setParameters(params);
        ((DefaultAsynchronousResponse) response).setSign((String) params.get("sign"));
        ((DefaultAsynchronousResponse) response).setMerchantNo((String) params.get("out_trade_no"));
        ((DefaultAsynchronousResponse) response).setPaymentNo((String) params.get("trade_no"));
        ((DefaultAsynchronousResponse) response).setTradingStatus((String) params.get("trade_status"));
        ((DefaultAsynchronousResponse) response).setOrderAmount((String) params.get("total_amount"));
        ((DefaultAsynchronousResponse) response).setSignValidate(signVerified(params));
        ((DefaultAsynchronousResponse) response).setAttachInfo((String) params.get("passback_params"));
        return response;
    }

    /**
     * 支付结果查询
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空,两者同时存在优先取支付宝交易号
     *
     * @param servletWebRequest
     * @param paymentResultQueryRequest
     * @return
     */
    @Override
    public PaymentResultQueryResponse paymentResultQuery(ServletWebRequest servletWebRequest, PaymentResultQueryRequest paymentResultQueryRequest) throws PayException {
        AlipayTradeQueryRequest request = getAlipayTradeQueryRequest(paymentResultQueryRequest);
        try {
            return paymentResultQueryResponseAdpter(alipayClient.execute(request));
        } catch (AlipayApiException e) {
            throw new PayException(e);
        }
    }


    /**
     * 发起支付宝退款请求
     *
     * @param servletWebRequest
     * @param refundRequest
     * @return
     */
    @Override
    public RefundResponse launchRefundProcessor(ServletWebRequest servletWebRequest, RefundRequest refundRequest) {
        AlipayTradeRefundRequest request = getAlipayTradeRefundRequest(refundRequest);
        try {
            return refundResponseAdpter(alipayClient.execute(request));
        } catch (AlipayApiException e) {
            throw new PayException(e);
        }
    }

    /**
     *  支付宝退款请求查询
     * @param servletWebRequest
     * @param refundResultQureyRequest
     * @return
     */
    @Override
    public RefundResultQureyResponse refundResultQuery(ServletWebRequest servletWebRequest, RefundResultQureyRequest refundResultQureyRequest) {
        AlipayTradeFastpayRefundQueryRequest request = getAlipayTradeFastpayRefundQueryRequest(refundResultQureyRequest);
        try {
            return  refundResultQueryResponseAdpter(alipayClient.execute(request));
        } catch (AlipayApiException e) {
            throw new PayException(e);
        }
    }

    /**
     *  组装支付宝退款查询需要的参数
     * @param refundResultQureyRequest
     * @return
     */
    protected AlipayTradeFastpayRefundQueryRequest getAlipayTradeFastpayRefundQueryRequest( RefundResultQureyRequest refundResultQureyRequest ){
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel  model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutRequestNo(refundResultQureyRequest.getMerchantNo());
        model.setTradeNo(refundResultQureyRequest.getPaymentNo());
        model.setOutRequestNo(refundResultQureyRequest.getRefundNo());
        request.setBizModel(model);
        return  request;
    }

    /**
     *  组装退款查询响应参数
     * @param alipayTradeFastpayRefundQueryResponse
     * @return
     */
    protected RefundResultQureyResponse refundResultQueryResponseAdpter( AlipayTradeFastpayRefundQueryResponse  alipayTradeFastpayRefundQueryResponse ){
        RefundResultQureyResponse response = new DefaultRefundResultQureyResponse();
        //获取参数集合和sign
        Map bordyMap =  JsonUtil.jsonToMap(alipayTradeFastpayRefundQueryResponse.getBody());
        Map parameterMap = (JSONObject) bordyMap.get("alipay_trade_fastpay_refund_query_response");
        //设置签名
        ((DefaultRefundResultQureyResponse) response).setSign((String) bordyMap.get("sign"));
        //返回的所有参数集合
        ((DefaultRefundResultQureyResponse) response).setParameters(parameterMap);
        //业务码
        ((DefaultRefundResultQureyResponse) response).setBusinessCode((String) parameterMap.get("sub_code"));
        //业务信息
        ((DefaultRefundResultQureyResponse) response).setBusinessMessage((String) parameterMap.get("sub_msg"));
        //网关码
        ((DefaultRefundResultQureyResponse) response).setGatewayCode((String) parameterMap.get("code"));
        //网关信息
        ((DefaultRefundResultQureyResponse) response).setGatewayMessage((String) parameterMap.get("msg"));

        ((DefaultRefundResultQureyResponse) response).setMerchantNo((String) parameterMap.get("out_trade_no"));
        ((DefaultRefundResultQureyResponse) response).setPaymentNo((String) parameterMap.get("trade_no"));
        ((DefaultRefundResultQureyResponse) response).setOrderAmount((String) parameterMap.get("total_amount"));
        ((DefaultRefundResultQureyResponse) response).setRefundAmount((String) parameterMap.get("refund_amount"));
        ((DefaultRefundResultQureyResponse) response).setRefundNo((String) parameterMap.get("out_request_no"));

        return response;
    }


    /**
     *  组装支付结果查询所需要的参数
     * @param paymentResultQueryRequest
     * @return
     */
    protected AlipayTradeQueryRequest getAlipayTradeQueryRequest(PaymentResultQueryRequest paymentResultQueryRequest){
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel alipayTradeQueryModel = new AlipayTradeQueryModel();
        alipayTradeQueryModel.setOutTradeNo(paymentResultQueryRequest.getMerchantNo());
        alipayTradeQueryModel.setTradeNo(paymentResultQueryRequest.getPaymentNo());
        request.setBizModel(alipayTradeQueryModel);
        return request;
    }

    /**
     * 将支付宝的退款请求结果转换为RefundResponse
     *
     * @param alipayTradeRefundResponse
     * @return
     */
    protected RefundResponse refundResponseAdpter(AlipayTradeRefundResponse alipayTradeRefundResponse) {
        RefundResponse refundResponse = new DefaultRefundResponse();
//        logger.info("这里是支付宝退款请求的响应结果:"+ CommonUtils.toString(alipayTradeRefundResponse));

        //获取参数集合和sign
        Map bordyMap =  JsonUtil.jsonToMap(alipayTradeRefundResponse.getBody());
        Map parameterMap = (JSONObject) bordyMap.get("alipay_trade_refund_response");

        //设置签名
        ((DefaultRefundResponse) refundResponse).setSign((String) bordyMap.get("sign"));
        //返回的所有参数集合
        ((DefaultRefundResponse) refundResponse).setParameters(parameterMap);
        //业务码
        ((DefaultRefundResponse) refundResponse).setBusinessCode((String) parameterMap.get("sub_code"));
        //业务信息
        ((DefaultRefundResponse) refundResponse).setBusinessMessage((String) parameterMap.get("sub_msg"));
         //网关码
        ((DefaultRefundResponse) refundResponse).setGatewayCode((String) parameterMap.get("code"));
        //网关信息
        ((DefaultRefundResponse) refundResponse).setGatewayMessage((String) parameterMap.get("msg"));

        //商户号
        ((DefaultRefundResponse) refundResponse).setMerchantNo((String) parameterMap.get("out_trade_no"));
        //订单号
        ((DefaultRefundResponse) refundResponse).setPaymentNo((String) parameterMap.get("trade_no"));
        //订单金额
        ((DefaultRefundResponse) refundResponse).setRefundAmount((String) parameterMap.get("refund_fee"));

        return refundResponse;
    }

    /**
     * 将支付宝的交易查询结果转换为PaymentResultQueryResponse
     *
     * @param alipayTradeQueryResponse
     * @return
     */
    protected PaymentResultQueryResponse paymentResultQueryResponseAdpter(AlipayTradeQueryResponse alipayTradeQueryResponse) {

//        logger.info("支付宝交易查询结果"+CommonUtils.toString(alipayTradeQueryResponse));
        PaymentResultQueryResponse response = new DefaultPaymentResultQueryResponse();
        //获取参数集合和sign
         Map bordyMap =  JsonUtil.jsonToMap(alipayTradeQueryResponse.getBody());
         Map parameterMap = (JSONObject) bordyMap.get("alipay_trade_query_response");
         //设置签名
        ((DefaultPaymentResultQueryResponse) response).setSign((String) bordyMap.get("sign"));
        //返回的所有参数集合
        ((DefaultPaymentResultQueryResponse) response).setParameters(parameterMap);

        //业务码
        ((DefaultPaymentResultQueryResponse) response).setBusinessCode((String) parameterMap.get("sub_code"));
        //业务信息
        ((DefaultPaymentResultQueryResponse) response).setBusinessMessage((String) parameterMap.get("sub_msg"));
        //网关码
        ((DefaultPaymentResultQueryResponse) response).setGatewayCode((String) parameterMap.get("code"));
        //网关信息
        ((DefaultPaymentResultQueryResponse) response).setGatewayMessage((String) parameterMap.get("msg"));

        //商户号
        ((DefaultPaymentResultQueryResponse) response).setMerchantNo((String) parameterMap.get("out_trade_no"));
         //订单号
        ((DefaultPaymentResultQueryResponse) response).setPaymentNo((String) parameterMap.get("trade_no"));
         //订单金额
        ((DefaultPaymentResultQueryResponse) response).setOrderAmount((String) parameterMap.get("total_amount"));
        //订单状态
        ((DefaultPaymentResultQueryResponse) response).setTradingStatus((String) parameterMap.get("trade_status"));
        return response;
    }


    /**
     * 将支付宝支付返回的结果转化为自定义的支付请求返回结果
     *
     * @param alipayResponse
     * @return
     */
    protected abstract PaymentResponse payResponseAdpter(AlipayResponse alipayResponse);

    /**
     * 组装支付宝支付请求所需要的参数 不同的支付方式使用的参数对象也是不同的
     *
     * @param paymentRequest
     * @return
     */
    protected abstract AlipayRequest getAlipayRequest(PaymentRequest paymentRequest);

    /**
     *  组装支付宝退款请求所需要的参数
     * @param refundRequest
     * @return
     */
    protected AlipayTradeRefundRequest getAlipayTradeRefundRequest(RefundRequest refundRequest){
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel alipayTradeRefundModel = new AlipayTradeRefundModel();
        alipayTradeRefundModel.setOutTradeNo(refundRequest.getMerchantNo());
        alipayTradeRefundModel.setTradeNo(refundRequest.getPaymentNo());
        alipayTradeRefundModel.setRefundAmount(refundRequest.getRefundAmount());
        alipayTradeRefundModel.setRefundReason(refundRequest.getRefundReason());
        alipayTradeRefundModel.setOutRequestNo(refundRequest.getRefundNo());
        request.setBizModel(alipayTradeRefundModel);

        return request;
    }


    /***
     *  支付宝支福请求的必要参数做非空校验
     * @param alipayRequest
     */
    protected abstract void paymentRequestParametersValidate(AlipayRequest alipayRequest);

    /**
     * 判断当前的AlipayObject是否支持
     *
     * @param cls
     * @return
     */
    protected abstract boolean isSupportAlipayObject(Class cls);

    /**
     * 校验异步通知签名
     *
     * @param params
     * @return
     */
    protected boolean signVerified(Map<String, String> params) {
        try {
            logger.info("校验签名的Map集合是:");
            MapUtils.printMap(params);
            AliPayProperties aliPay = luckyProperties.getPay().getAli();
            return AlipaySignature.rsaCheckV1(params, aliPay.getAlipayPublicKey(), aliPay.getCharset(), aliPay.getSignType()); //调用SDK验证签名
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
    }

}
