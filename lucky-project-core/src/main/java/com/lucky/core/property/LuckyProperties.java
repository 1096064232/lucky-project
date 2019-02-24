package com.lucky.core.property;


import com.lucky.core.property.pay.PayProperties;
import com.lucky.core.property.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  项目的配置项
 */
@ConfigurationProperties(prefix = "lucky")
public class LuckyProperties {

    private PayProperties pay = new PayProperties();

    private SecurityProperties security = new SecurityProperties();

    public PayProperties getPay() {
        return pay;
    }

    public void setPay(PayProperties pay) {
        this.pay = pay;
    }

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }
}
