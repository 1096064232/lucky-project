package com.lucky.browser.security.authentication.config;

import com.lucky.browser.security.session.BrowserExpiredSessionStrategy;
import com.lucky.browser.security.session.BrowserInvalidSessionStrategy;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.authentication.LuckyUserDetailsService;
import com.lucky.core.security.config.ChannelSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;


/**
 * 浏览器的安全配置
 */
@Configuration
public class BrowserSecurityConfig extends ChannelSecurityConfig {


    @Autowired
    LuckyProperties luckyProperties;

    @Autowired(required = false)
    PersistentTokenRepository tokenRepository;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    LuckyUserDetailsService luckyUserDetailsService;

    @Autowired
    SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    InvalidSessionStrategy invalidSessionStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      channelAuthenticationConfig(http);

        http
                .sessionManagement()
                   .maximumSessions(luckyProperties.getSecurity().getAuthentication().getBrowser().getMaximumSessions())
                   .expiredSessionStrategy(sessionInformationExpiredStrategy)
                   .maxSessionsPreventsLogin(luckyProperties.getSecurity().getAuthentication().getBrowser().isMaxSessionsPreventsLogin())
                   .and()
                   .invalidSessionStrategy(invalidSessionStrategy)
                   .and()
                .rememberMe()
                  .tokenRepository(tokenRepository)
                  .rememberMeParameter(luckyProperties.getSecurity().getAuthentication().getBrowser().getRememberMeParameterName())
                  .tokenValiditySeconds(luckyProperties.getSecurity().getAuthentication().getBrowser().getRememberMeSecond())
                  .userDetailsService(luckyUserDetailsService)
                  .authenticationSuccessHandler(authenticationSuccessHandler);
    }
}
