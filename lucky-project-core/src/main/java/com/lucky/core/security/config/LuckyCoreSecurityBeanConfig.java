package com.lucky.core.security.config;

import com.lucky.core.security.authority.AuthorizeConfigManager;
import com.lucky.core.security.authority.LuckyCoreAuthorizeConfigManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

@Configuration
public class LuckyCoreSecurityBeanConfig {

    /**
     * 默认的密码加密方式是：PasswordEncoder
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AntPathMatcher.class)
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    /**
     * AuthorizeConfigManager配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(AuthorizeConfigManager.class)
    public AuthorizeConfigManager authorizeConfigManager() {
        return new LuckyCoreAuthorizeConfigManager();
    }
}
