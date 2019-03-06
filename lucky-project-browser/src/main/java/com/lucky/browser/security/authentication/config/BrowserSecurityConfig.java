package com.lucky.browser.security.authentication.config;

import com.lucky.core.security.config.ChannelSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


/**
 * 浏览器的安全配置
 */
@Configuration
public class BrowserSecurityConfig extends ChannelSecurityConfig {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        HttpSecurity   nowHttp = channelAuthenticationConfig(http);

    }
}
