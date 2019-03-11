package com.lucky.core.security.social.wxpublic.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.core.exception.SocialException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

public class WeixinPublicImpl extends AbstractOAuth2ApiBinding implements WeixinPublic {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = " https://api.weixin.qq.com/sns/userinfo?openid=%s&lang=zh_CN";

    /**
     * @param accessToken
     */
    public WeixinPublicImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeixinPublicUserInfo getUserInfo(String openId) {
        String url = String.format(URL_GET_USER_INFO, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(result, "errcode")) {
            logger.error("openid换取WeixinPublicUserInfo返回错误的JSON数据包：{}", result);
            throw new SocialException("获取用户信息失败:"+result);
        }
        try {
            return objectMapper.readValue(result, WeixinPublicUserInfo.class);
        } catch (Exception e) {
            logger.error("openid换取WeixinPublicUserInfo的JSON数据包：{}转化为WeixinPublicUserInfo对象出现错误：{}", result,e.getMessage());
            throw new SocialException("获取用户信息失败",e);
        }
    }
}
