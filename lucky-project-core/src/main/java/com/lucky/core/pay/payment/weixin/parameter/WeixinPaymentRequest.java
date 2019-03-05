package com.lucky.core.pay.payment.weixin.parameter;

import com.lucky.common.util.BigDecimalCalculationUtil;
import com.lucky.core.pay.parameter.impl.DefaultPaymentRequest;

/**
 *  增加微信支付需要的参数
 */
public class WeixinPaymentRequest extends DefaultPaymentRequest {

    /**
     *  终端IP  调用微信支付API的机器IP
     *  支持IPV4和IPV6两种格式的IP地址。
     */
    private String spbillCreateIp;

    /**
     *  商品ID
     *  trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     */
    private String productId;

    /**
     *  标准的是以元为单位，但微信是以分为单位，进行处理
     * @return
     */
    @Override
    public String getOrderAmount() {
        return  new Integer(new Double(BigDecimalCalculationUtil.div(Double.parseDouble(super.getOrderAmount()),100)).intValue()).toString();
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


}
