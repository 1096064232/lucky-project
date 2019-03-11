package com.lucky.core.security.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;


public class LuckySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public LuckySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /**
     * SpringSocialConfigurer在把SocialAuthenticationFilter过滤器加入到Security之前调用此方法 对SocialAuthenticationFilter
     * 进行处理 重写此方法，让social认证路径变成可配置的
     * @param object
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }


}
