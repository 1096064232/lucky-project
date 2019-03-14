package com.lucky.app.security.authentication.config;

import com.lucky.core.security.authentication.LuckyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 *  认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig
//        extends AuthorizationServerConfigurerAdapter
{

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private LuckyUserDetailsService luckyUserDetailsService;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//        endpoints
//                .authenticationManager(authenticationManager)
//                .userDetailsService(luckyUserDetailsService);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//
//    }
}
