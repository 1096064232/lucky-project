package com.lucky.demo.app.security.authentication;

import com.lucky.core.security.authority.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public HttpSecurity config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config)throws Exception {
        return config
                        .antMatchers("/user/regist").permitAll()
                        .antMatchers("/user/*").hasAnyRole("ROLE_USER")
                        .and()
                        .csrf()
                        .disable();
    }
}
