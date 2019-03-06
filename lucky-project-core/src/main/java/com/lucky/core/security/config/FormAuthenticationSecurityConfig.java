package com.lucky.core.security.config;

import com.lucky.core.property.LuckyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 *  无论是浏览器还是APP都会用到表单登录
 */
public class FormAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LuckyProperties luckyProperties;

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;


    protected HttpSecurity applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage(luckyProperties.getSecurity().getAuthentication().getUnAuthenticationUrl())
                .loginProcessingUrl(luckyProperties.getSecurity().getAuthentication().getFormLogin().getLoginProcessingUrl())
                .usernameParameter(luckyProperties.getSecurity().getParameter().getFormLogin().getUsernameParameterName())
                .passwordParameter(luckyProperties.getSecurity().getParameter().getFormLogin().getPasswordParameterName())
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        return http;
    }

}
