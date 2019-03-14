package com.lucky.core.security.config;

import com.lucky.core.security.authentication.SecurityConfigurerProvider;
import com.lucky.core.security.authority.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.social.security.SpringSocialConfigurer;

import java.util.Map;

/**
 * 通用的Security配置
 */
public class ChannelSecurityConfig extends FormAuthenticationSecurityConfig {


    @Autowired
    Map<String, SecurityConfigurerProvider> securityConfigurerProviderMap;

    @Autowired
    AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    SpringSocialConfigurer luckySpringSocialConfigurer;

    public HttpSecurity channelAuthenticationConfig(HttpSecurity http)throws Exception {

        //基本用户密码认证配置
        HttpSecurity  passHttp = applyPasswordAuthenticationConfig(http);

        passHttp.apply(luckySpringSocialConfigurer);

        //收集系统中所有的认证配置
        for (String key : securityConfigurerProviderMap.keySet()) {

            passHttp.apply(securityConfigurerProviderMap.get(key));
        }

        //收集系统中所有权限配置
        HttpSecurity  result =  authorizeConfigManager.config(passHttp.authorizeRequests());

        return result;
    }


}
