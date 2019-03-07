package com.lucky.core.property.security.auhentication;

public class BrowserAuthenticationProperties {

    /**
     *  登录页面配置
     */
    private String loginPage;

    /**
     *  记住我秒数
     */
    private int rememberMeSecond;

    /**
     *  是否在启动时创建记住我的表
     */
    private boolean createTableOnStartupToRemeberMe =false;

    /**
     *   记住我的参数名称
     */
    private String rememberMeParameterName = "remeber-me";

    /**
     *  允许登录的最大session数 -1 无限制
     */
    private int maximumSessions = -1;


    /**
     *  达到最大session数后是禁止后来Session 登录
     */
    private boolean maxSessionsPreventsLogin = true;


    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }

    public String getRememberMeParameterName() {
        return rememberMeParameterName;
    }

    public void setRememberMeParameterName(String rememberMeParameterName) {
        this.rememberMeParameterName = rememberMeParameterName;
    }

    public int getRememberMeSecond() {
        return rememberMeSecond;
    }

    public void setRememberMeSecond(int rememberMeSecond) {
        this.rememberMeSecond = rememberMeSecond;
    }

    public boolean isCreateTableOnStartupToRemeberMe() {
        return createTableOnStartupToRemeberMe;
    }

    public void setCreateTableOnStartupToRemeberMe(boolean createTableOnStartupToRemeberMe) {
        this.createTableOnStartupToRemeberMe = createTableOnStartupToRemeberMe;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
