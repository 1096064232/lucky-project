package com.lucky.app.security.authentication.openId;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private String openIdParameter = "openId";

    private String providerIdParameter = "providerId";

    private boolean postOnly = true;

    public OpenIdAuthenticationFilter(String pattern) {
        super(new AntPathRequestMatcher(pattern, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String openId = this.obtainOpenId(request);
            String providerId = this.obtainProvider(request);
            if (openId == null) {
                openId = "";
            }

            if (providerId == null) {
                providerId = "";
            }

            openId = openId.trim();

            providerId = providerId.trim();

            OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(this.openIdParameter);
    }

    protected String obtainProvider(HttpServletRequest request) {
        return request.getParameter(this.providerIdParameter);
    }

    protected void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public void setOpenIdParameter(String openIdParameter) {
        this.openIdParameter = openIdParameter;
    }

    public void setProviderIdParameter(String providerIdParameter) {
        this.providerIdParameter = providerIdParameter;
    }

}
