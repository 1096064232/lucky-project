package com.lucky.core.property.pay;

import com.lucky.core.property.pay.weixin.WeixinPayProperties;
import com.lucky.core.property.pay.ali.AliPayProperties;

/**
 *  支付的配置项
 */
public class PayProperties {


    private AliPayProperties ali = new AliPayProperties();

    private WeixinPayProperties weixin = new WeixinPayProperties();

    public AliPayProperties getAli() {
        return ali;
    }

    public void setAli(AliPayProperties ali) {
        this.ali = ali;
    }

    public WeixinPayProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinPayProperties weixin) {
        this.weixin = weixin;
    }
}
