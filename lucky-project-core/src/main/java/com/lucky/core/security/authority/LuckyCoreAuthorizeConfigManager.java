package com.lucky.core.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.Set;

public class LuckyCoreAuthorizeConfigManager implements AuthorizeConfigManager {

    /**
     *  收集系统中所有的AuthorizeConfigProvider的接口
     */
    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviderSet;

    @Override
    public HttpSecurity config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception{
        for (AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviderSet) {
            authorizeConfigProvider.config(config);
        }
        config.anyRequest().authenticated();
        return config.and();
    }
}
