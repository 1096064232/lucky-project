package com.lucky.app.security.authentication.openId;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6852375231638510378L;

    private Object principal;

    private String providerId;


    /**
     *  组装认证请求时构造的认证方法
     * @param openId
     * @param providerId
     */
    public OpenIdAuthenticationToken(String openId,String providerId) {
        super((Collection)null);
        this.principal=openId;
        this.providerId=providerId;
    }

    /**
     *  认证成功后调用的构造方法
     * @param principal
     * @param authorities
     */
    public OpenIdAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal=principal;
    }



    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
