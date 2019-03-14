package com.lucky.app.security.authentication.config;

import com.lucky.app.security.authentication.InMemoryValidateCodeRepository;
import com.lucky.app.security.authentication.OAuth2TokenHandler;
import com.lucky.core.security.verification.ValidateCodeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppSecurityBeanConfig {

    /**
     *  配置校验码存贮策略
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeRepository.class)
    public ValidateCodeRepository validateCodeRepository(){

        return new InMemoryValidateCodeRepository();
    }


    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler(){

        return new OAuth2TokenHandler();
    }
}
