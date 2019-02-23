package com.lucky.core.pay;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

/**
 *  支付/退款 处理器  的处理器 这里是开始整个支付流程开始的地方
 */
public class PaymentProcessorHolder {

    /**
     *  收集系统中所有的{@link com.lucky.core.pay.PaymentProcessor}接口实现类
     */
    @Autowired
    private Map<String, PaymentProcessor> paymentProcessors;

    /**
     * 根据不同的支付方式调用不同的支付处理器
     * @param type
     * @return
     */
    public PaymentProcessor findPaymentProcessor(PaymentTypeEnum type) {
        return findPaymentProcessor(type.getPaymentType());
    }

    /**
     * 根据不同的支付方式调用不同的支付处理器
     * @param type
     * @return
     */
    public PaymentProcessor findPaymentProcessor(String type) {
        String name = type + PaymentProcessor.class.getSimpleName();
        PaymentProcessor processor = paymentProcessors.get(name);
        if (processor == null) {
            return null;
        }
        return processor;
    }

}
