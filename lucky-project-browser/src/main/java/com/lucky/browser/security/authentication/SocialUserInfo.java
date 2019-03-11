package com.lucky.browser.security.authentication;

public class SocialUserInfo {

    private String providerId;

    private String ProviderUserId;

    private String nickName;

    private String headImage;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return ProviderUserId;
    }

    public void setProviderUserId(String providerUserId) {
        ProviderUserId = providerUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
