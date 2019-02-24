package com.lucky.demo.browser.pay.service.impl.ali;

import com.lucky.common.util.CommonUtils;

import com.lucky.common.util.MapUtils;
import com.lucky.core.exception.PayException;
import com.lucky.core.pay.PaymentProcessorHolder;
import com.lucky.core.pay.PaymentTypeEnum;
import com.lucky.core.pay.parameter.*;
import com.lucky.core.pay.parameter.impl.DefaultPaymentRequest;
import com.lucky.core.property.LuckyProperties;
import com.lucky.demo.browser.pay.service.impl.AbstractPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  测试支付宝手机网站支付
 */
@Service("aliWapPayServiceImpl")
public class AliWapPayServiceImpl extends AbstractPayService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PaymentProcessorHolder paymentProcessorHolder;

    @Autowired
    LuckyProperties luckyProperties;
    /**
     *  发起支付宝电脑网站请求
     * @param request
     */
    @Override
    public void payment(ServletWebRequest request) {
        //获取请求中的支付参数
        DefaultPaymentRequest paymentRequest = (DefaultPaymentRequest) getPaymentRequest(request);
        paymentRequest.setNotifyUrl("http://ouyangfan.natapp1.cc/pay/ali/wap/notify");
        paymentRequest.setReturnUrl("http://ouyangfan.natapp1.cc/pay/ali/wap/return");
        PaymentResponse paymentResponse = null;
        try {
            paymentResponse = paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).launchPaymentProcessor(request,paymentRequest);
        } catch (PayException e) {
            e.printStackTrace();
        }
        logger.info("发起支付宝手机网站支付返回的结果是:{}", CommonUtils.toString(paymentResponse));
        String form = (String) paymentResponse.getBody(); //调用SDK生成表单
        HttpServletResponse httpResponse = request.getResponse();
        httpResponse.setContentType("text/html;charset=" + luckyProperties.getPay().getAli().getCharset());
        try {
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpResponse.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  发起支付结果查询请求
     * @param request
     */
    @Override
    public void paymentQurey(ServletWebRequest request) {
        PaymentResultQueryRequest paymentResultQueryRequest =getPaymentResultQueryRequest(request);
        PaymentResultQueryResponse paymentResultQueryResponse =  paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).paymentResultQuery(request,paymentResultQueryRequest);
        logger.info("这是支付宝手机网站支付的支付结果查询结果:{}",CommonUtils.toString(paymentResultQueryResponse));
    }

    /**
     *  发起退款请求
     * @param request
     */
    @Override
    public void refund(ServletWebRequest request) {
        RefundRequest refundRequest = getRefundRequest(request);
        RefundResponse refundResponse =  paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).launchRefundProcessor(request,refundRequest);
        logger.info("这是支付宝手机网站支付的退款请求结果:{}",CommonUtils.toString(refundResponse));
    }

    /**
     *  发起退款结果查询请求
     * @param request
     */
    @Override
    public void refundQuery(ServletWebRequest request) {
        RefundResultQureyRequest refundResultQureyRequest = getRefundResultQureyRequest(request);
        RefundResultQureyResponse refundResponse =  paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).refundResultQuery(request,refundResultQureyRequest);
        logger.info("这是支付宝手机网站支付的退款查询请求结果:{}",CommonUtils.toString(refundResponse));
    }

    /**
     *  订单关闭
     */
    @Override
    public void tradeClose(ServletWebRequest servletWebRequest) {
        logger.info("支付宝电脑网站订单关闭请求");
        TradeCloseRequest tradeCloseRequest = getTradeCloseRequest(servletWebRequest);
        TradeClsoeResponse tradeClsoeResponse = paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).tradeClose(servletWebRequest,tradeCloseRequest);
        logger.info("支付宝电脑网站订单关闭响应:{}",CommonUtils.toString(tradeClsoeResponse));
    }

    /**
     *  异步通知
     * @param servletWebRequest
     */
    @Override
    public void notifyNote(ServletWebRequest servletWebRequest) {
        logger.info("这里是支付宝手机网站支付异步通知处理结果");
        AsynchronousResponse asynchronousResponse =  paymentProcessorHolder.findPaymentProcessor(PaymentTypeEnum.ALI_WAP).callBacksHandler(servletWebRequest);
        logger.info("这里是支付宝手机网站支付异步通知处理结果,处理结果是:{}",CommonUtils.toString(asynchronousResponse));
    }

    /**
     *  同步通知
     * @param servletWebRequest
     */
    @Override
    public void returnNote(ServletWebRequest servletWebRequest) {
        logger.info("这里是支付宝手机网站支付同步通知处理结果");
        MapUtils.printMap(servletWebRequest.getParameterMap());
    }

}
