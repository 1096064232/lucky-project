package com.lucky.core.security.authority;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.constant.ValidateCodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class LuckyCoreAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    LuckyProperties luckyProperties;

    @Override
    public HttpSecurity config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
     return    config.antMatchers(
                ValidateCodeConstant.CODE_URL+"/*",
                luckyProperties.getSecurity().getAuthentication().getUnAuthenticationUrl(),
                luckyProperties.getSecurity().getAuthentication().getMobileLogin().getLoginProcessingUrl(),
                luckyProperties.getSecurity().getAuthentication().getFormLogin().getLoginProcessingUrl()
        ).permitAll().and();
    }
}
