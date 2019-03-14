package com.lucky.app.security.authentication.config;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.authentication.SecurityConfigurerProvider;
import com.lucky.core.security.authority.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import java.util.Map;


/**
 *  APP安全配置
 */
@Configuration
@EnableResourceServer
public class AppSecurityConfig
        extends ResourceServerConfigurerAdapter
{

    @Autowired
    LuckyProperties luckyProperties;

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    Map<String, SecurityConfigurerProvider> securityConfigurerProviderMap;

    @Autowired
    AuthorizeConfigManager authorizeConfigManager;

//    @Autowired
//    SpringSocialConfigurer luckySpringSocialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        //收集系统中所有的认证配置
        for (String key : securityConfigurerProviderMap.keySet()) {

            http.apply(securityConfigurerProviderMap.get(key));
        }

        authorizeConfigManager.config(http.authorizeRequests());

        http
                   .formLogin()
                .loginPage(luckyProperties.getSecurity().getAuthentication().getUnAuthenticationUrl())
                .loginProcessingUrl(luckyProperties.getSecurity().getAuthentication().getFormLogin().getLoginProcessingUrl())
                .usernameParameter(luckyProperties.getSecurity().getParameter().getFormLogin().getUsernameParameterName())
                .passwordParameter(luckyProperties.getSecurity().getParameter().getFormLogin().getPasswordParameterName())
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }
}
