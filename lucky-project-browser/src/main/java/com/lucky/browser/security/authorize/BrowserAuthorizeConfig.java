package com.lucky.browser.security.authorize;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.constant.SecurityConstants;
import com.lucky.core.security.authority.AuthorizeConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class BrowserAuthorizeConfig implements AuthorizeConfigProvider {

    @Autowired
    LuckyProperties luckyProperties;

    @Override
    public HttpSecurity config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
                  return config.antMatchers(
                          luckyProperties.getSecurity().getAuthentication().getBrowser().getLoginPage(),
                          SecurityConstants.SOCIAL_USER_URL
                  ).permitAll().and();
    }
}
