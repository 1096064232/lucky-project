package com.lucky.core.pay.payment.weixin.pc;

import com.lucky.core.exception.PayException;
import com.lucky.core.pay.parameter.PaymentRequest;
import com.lucky.core.pay.payment.weixin.AbstractWeixinPaymentProcessor;
import com.lucky.core.pay.payment.weixin.parameter.WeixinPaymentRequest;
import com.lucky.core.property.LuckyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWeixinPCPaymentProcessor extends AbstractWeixinPaymentProcessor {

    /**
     *  电脑扫码支付的交易类型
     */
    private static final String TRADE_TYPE = "NATIVE";

    @Autowired
    LuckyProperties luckyProperties;

    /**
     *  组装微信电脑native支付请求的参数
     * @param paymentRequest
     * @return
     */
    @Override
    protected Map<String, String> getWeixnPaymentRequest(PaymentRequest paymentRequest) {
        if(WeixinPaymentRequest.class.isAssignableFrom(paymentRequest.getClass())){
            WeixinPaymentRequest request = (WeixinPaymentRequest) paymentRequest;
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", request.getOrderTitle());
            data.put("out_trade_no", request.getMerchantNo());
            data.put("sign_type", request.getProductId());
            data.put("total_fee", request.getOrderAmount()); //总金额为分  WeixinPaymentRequest中已做处理
            data.put("spbill_create_ip", request.getSpbillCreateIp());
            data.put("notify_url", request.getNotifyUrl());
            data.put("trade_type", getTradeType());  // 此处指定为扫码支付
            data.put("product_id",request.getProductId()); //trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
            return  data;
        }
       throw new PayException("请传如com.lucky.core.pay.payment.weixin.pc.WeixinPaymentRequest类型的参数");
    }

    /**
     *  获取交易类型
     * @return
     */
    @Override
    protected String getTradeType() {
        return TRADE_TYPE;
    }
}
