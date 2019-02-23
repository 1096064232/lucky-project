package com.lucky.core.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.lucky.core.pay.PaymentProcessor;
import com.lucky.core.pay.PaymentProcessorHolder;
import com.lucky.core.pay.PaymentTypeEnum;
import com.lucky.core.pay.payment.ali.pc.AliPcPaymentProcessor;
import com.lucky.core.pay.payment.ali.wap.AliWapPaymentProcessor;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.ali.AliPayProperties;
import com.lucky.core.property.constant.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支付请求的bean配置
 */
@Configuration
public class PaymentBeanConfig {

    @Autowired
    LuckyProperties luckyProperties;

    /**
     * 配置系统默认的支付处理支持器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(PaymentProcessorHolder.class)
    public PaymentProcessorHolder paymentProcessorHolder() {
        return new PaymentProcessorHolder();
    }

    /**
     * 配置支付宝支付客户端
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(AlipayClient.class)
    public AlipayClient pcAlipayClient() {
        AliPayProperties aliPay= luckyProperties.getPay().getAli();
        return new DefaultAlipayClient(
                aliPay.getWayUrl(),
                aliPay.getAppid(),
                aliPay.getMerchantPrivateKey(),
                aliPay.getFormat(),
                aliPay.getCharset(),
                aliPay.getAlipayPublicKey(),
                aliPay.getSignType());
    }

    /**
     *  支付宝电脑网站支付处理逻辑
     * @return
     */
    @Bean(name = PayConstant.ALI_PC_WEB+"PaymentProcessor")
    @ConditionalOnMissingBean(name = PayConstant.ALI_PC_WEB+"PaymentProcessor")
    public PaymentProcessor aliPcWebPaymentProcessor(){
        return new AliPcPaymentProcessor();
    }


    /**
     *  支付宝手机网站支付处理逻辑
     * @return
     */
    @Bean(name =PayConstant.ALI_WAP+"PaymentProcessor")
    @ConditionalOnMissingBean(name = PayConstant.ALI_WAP+"PaymentProcessor")
    public PaymentProcessor aliWapPaymentProcessor(){
        return new AliWapPaymentProcessor();
    }
}
