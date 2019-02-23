package com.lucky.core.pay;

import com.lucky.core.property.constant.PayConstant;

/**
 *  支持的支付类型
 */
public enum PaymentTypeEnum {

    /**
     * 支付宝电脑网站支付
     */
    ALI_PC_WEB {
        @Override
        public String getPaymentType() {
            return PayConstant.ALI_PC_WEB;
        }
    },

    /**
     * 支付包手机网站支付
     */
    ALI_WAP {
        @Override
        public String getPaymentType() {
            return PayConstant.ALI_WAP;
        }
    },

    /**
     * 支付包APP支付
     */
    ALI_APP {
        @Override
        public String getPaymentType() {
            return PayConstant.ALI_APP;
        }
    },

    /**
     * 微信电脑网站支付
     */
    WEIXIN_PC_WEB {
        @Override
        public String getPaymentType() {
            return PayConstant.WEIXIN_PC_WEB;
        }
    },

    /**
     * 微信手机网站支付（微信浏览器之外的浏览器支付）
     */
    WEIXIN_H5 {
        @Override
        public String getPaymentType() {
            return PayConstant.WEIXIN_H5;
        }
    },

    /**
     * 微信内置浏览器支付
     */
    WEIXIN_JS {
        @Override
        public String getPaymentType() {
            return PayConstant.WEIXIN_JS;
        }
    };

    public abstract String getPaymentType();
}
