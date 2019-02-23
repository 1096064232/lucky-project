package com.lucky.demo.browser.pay.service.impl;

import com.lucky.core.exception.PayException;
import com.lucky.core.pay.parameter.PaymentRequest;
import com.lucky.core.pay.parameter.PaymentResultQueryRequest;
import com.lucky.core.pay.parameter.RefundRequest;
import com.lucky.core.pay.parameter.RefundResultQureyRequest;
import com.lucky.core.pay.parameter.impl.DefaultPaymentRequest;
import com.lucky.core.pay.parameter.impl.DefaultPaymentResultQueryRequest;
import com.lucky.core.pay.parameter.impl.DefaultRefundRequest;
import com.lucky.core.pay.parameter.impl.DefaultRefundResultQureyRequest;
import com.lucky.demo.browser.pay.service.PayService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.UUID;

public abstract class AbstractPayService implements PayService {

    /**
     * 获取支付请求需要的参数
     *
     * @param request
     * @return
     */
    protected PaymentRequest getPaymentRequest(ServletWebRequest request) {
        //获取订单编号
        String orderNo = ServletRequestUtils.getStringParameter(request.getRequest(), "orderNo", UUID.randomUUID().toString());
        //获取订单金额
        double orderAmount = ServletRequestUtils.getDoubleParameter(request.getRequest(), "orderAmount", 520.52);
        //获取订单标题
        String orderTitle = ServletRequestUtils.getStringParameter(request.getRequest(), "orderTitle", "测试支付宝电脑网站支付");
        //获取订单详情
        String orderDetails = ServletRequestUtils.getStringParameter(request.getRequest(), "orderDetails", "测试支付宝电脑网站支付的订单详情");
        // 获取附加消息
        String attachInfo = ServletRequestUtils.getStringParameter(request.getRequest(), "attachInfo", "测试支付宝电脑网站支付的附加消息");
        // 组装需要的支付参数
        DefaultPaymentRequest paymentRequest = new DefaultPaymentRequest();
        paymentRequest.setMerchantNo(orderNo);
        paymentRequest.setAttachInfo(attachInfo);
        paymentRequest.setOrderAmount(new Double(orderAmount).toString());
        paymentRequest.setOrderTitle(orderTitle);
        paymentRequest.setOrderDetails(orderDetails);
        return paymentRequest;
    }

    /**
     * 获取支付结果查询需要的参数
     *
     * @param request
     * @return
     */
    protected PaymentResultQueryRequest getPaymentResultQueryRequest(ServletWebRequest request) {
        String merchantNo = ServletRequestUtils.getStringParameter(request.getRequest(), "merchantNo", "");
        String paymentNo = ServletRequestUtils.getStringParameter(request.getRequest(), "paymentNo", "");
        DefaultPaymentResultQueryRequest resultQueryRequest = new DefaultPaymentResultQueryRequest();
        if (StringUtils.isBlank(merchantNo) && StringUtils.isBlank(paymentNo)) {
            throw new PayException("商户号和支付订单号不能同时为空");
        }
        resultQueryRequest.setMerchantNo(merchantNo); //商户号
        resultQueryRequest.setPaymentNo(paymentNo); //第三方支付号
        return resultQueryRequest;
    }

    /**
     * 获取退款请求所需要的参数
     *
     * @param request
     * @return
     */
    protected RefundRequest getRefundRequest(ServletWebRequest request) {
        String merchantNo = ServletRequestUtils.getStringParameter(request.getRequest(), "merchantNo", "");
        String paymentNo = ServletRequestUtils.getStringParameter(request.getRequest(), "paymentNo", "");
        String refundNo = ServletRequestUtils.getStringParameter(request.getRequest(), "refundNo", "");
        String refundAmount = ServletRequestUtils.getStringParameter(request.getRequest(), "refundAmount", "");
        String refundReason = ServletRequestUtils.getStringParameter(request.getRequest(), "refundReason", "");

        DefaultRefundRequest refundRequest = new DefaultRefundRequest();
        if (StringUtils.isBlank(merchantNo) && StringUtils.isBlank(paymentNo)) {
            throw new PayException("商户号和支付订单号不能同时为空");
        }
        refundRequest.setMerchantNo(merchantNo); //商户号
        refundRequest.setPaymentNo(paymentNo); //第三方支付号
        refundRequest.setRefundNo(refundNo);//退款请求号
        refundRequest.setRefundAmount(refundAmount); //退款金额
        refundRequest.setRefundReason(refundReason); //退款原因
        return refundRequest;

    }

    /**
     * 获取退款结果查询所需要的参数
     *
     * @param request
     * @return
     */
    protected RefundResultQureyRequest getRefundResultQureyRequest(ServletWebRequest request) {

        String merchantNo = ServletRequestUtils.getStringParameter(request.getRequest(), "merchantNo", "");
        String paymentNo = ServletRequestUtils.getStringParameter(request.getRequest(), "paymentNo", "");
        String refundNo = ServletRequestUtils.getStringParameter(request.getRequest(), "refundNo", "");


        DefaultRefundResultQureyRequest refundResultQureyRequest = new DefaultRefundResultQureyRequest();
        if (StringUtils.isBlank(merchantNo) && StringUtils.isBlank(paymentNo)) {
            throw new PayException("商户号和支付订单号不能同时为空");
        }
        if (StringUtils.isBlank(refundNo)) {
            throw new PayException("退款请求号不能为空");
        }
        refundResultQureyRequest.setMerchantNo(merchantNo); //商户号
        refundResultQureyRequest.setPaymentNo(paymentNo); //第三方支付号
        refundResultQureyRequest.setRefundNo(refundNo);//退款请求号

        return refundResultQureyRequest;

    }

}
