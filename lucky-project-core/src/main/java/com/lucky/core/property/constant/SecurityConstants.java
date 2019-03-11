package com.lucky.core.property.constant;

/**
 * @Author ouyangfan
 * @Date 2019/3/521:16
 **/
public interface SecurityConstants {

    /**
     *  未认证时跳转的URL
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/unAuthentication";

    /**
     *  获取当前认证信息的url
     */
    String PRINCIPAL_URL = "/me";

    /**
     *  用户的社交账号未绑定系统用户时，访问该路径获取用户的社交信息
     */
    String SOCIAL_USER_URL = "/social/user";
}
