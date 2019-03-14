package com.lucky.core.property.security.auhentication;

public class SocialAuthenticationProperties {

    /***
     *  发起社交登录的url前缀
     */
    private String filterProcessesUrl="/auth";

    /**
     * 社交登录表的前缀
     */
    private String tablePrefix="/ouyang";

    /**
     *  社交登录用户注册与绑定的页面url
     */
    private String signupUrl;

    /**
     *  用社交账号的openid登录的url(app)
     */
    private String openIdAuthenticaitonUrl="/openid/authentication";

    /**
     *  QQ登录配置项
     */
    private QQAuthenticationProperties qq = new QQAuthenticationProperties();

    /**
     *  微信登录配置项
     */
    private WeixinAuthenticationProperties weixin = new WeixinAuthenticationProperties();

    public String getOpenIdAuthenticaitonUrl() {
        return openIdAuthenticaitonUrl;
    }

    public void setOpenIdAuthenticaitonUrl(String openIdAuthenticaitonUrl) {
        this.openIdAuthenticaitonUrl = openIdAuthenticaitonUrl;
    }

    public String getSignupUrl() {
        return signupUrl;
    }

    public void setSignupUrl(String signupUrl) {
        this.signupUrl = signupUrl;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public WeixinAuthenticationProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinAuthenticationProperties weixin) {
        this.weixin = weixin;
    }

    public QQAuthenticationProperties getQq() {
        return qq;
    }

    public void setQq(QQAuthenticationProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
