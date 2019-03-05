package com.lucky.core.security.config;

import com.lucky.core.security.authentication.LcukySecurityConfigurer;
import com.lucky.core.security.verification.config.ValidateCodeSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Map;

/**
 * @Author ouyangfan
 * @Date 2019/3/223:01
 **/
@Configuration
public class LuckySecurityConfigurer extends FormAuthenticationSecurityConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    Map<String, LcukySecurityConfigurer> lcukySecurityConfigurerMap;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        LcukySecurityConfigurer lcukySecurityConfigurer = null;
        for (String key : lcukySecurityConfigurerMap.keySet()) {
            lcukySecurityConfigurer = lcukySecurityConfigurerMap.get(key);
            logger.debug("当前项目中LcukySecurityConfigurer的实现类有:{}", lcukySecurityConfigurer.getClass().getName());
            http.apply(lcukySecurityConfigurer);
        }
        applyPasswordAuthenticationConfig(http);
        http
                .authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }

}
