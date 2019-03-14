package com.lucky.app.security.authentication.openId;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.authentication.LuckyUserDetailsService;
import com.lucky.core.security.authentication.SecurityConfigurerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

@Component
public class OpenIdAuthenticationConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
        implements SecurityConfigurerProvider<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private LuckyProperties luckyProperties;

    @Autowired
    private LuckyUserDetailsService luckyUserDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        OpenIdAuthenticationFilter filter  =new OpenIdAuthenticationFilter(luckyProperties.getSecurity().getAuthentication().getSocial().getOpenIdAuthenticaitonUrl());

        filter.setOpenIdParameter(luckyProperties.getSecurity().getParameter().getOpenIdLogin().getOpenIdParameterName());
        filter.setProviderIdParameter(luckyProperties.getSecurity().getParameter().getOpenIdLogin().getProviderIdParameterName());
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);

        filter.afterPropertiesSet();

        OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
        provider.setLuckyUserDetailsService(luckyUserDetailsService);
        provider.setUsersConnectionRepository(usersConnectionRepository);

        http
                .authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
