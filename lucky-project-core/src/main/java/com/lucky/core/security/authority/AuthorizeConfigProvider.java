package com.lucky.core.security.authority;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 *  权限配置接口
 */
public interface AuthorizeConfigProvider {

    /**
     *  权限配置
     * @param config
     */
    HttpSecurity config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) throws Exception;

}
