package com.lucky.app.security.authentication.openId;

import com.lucky.core.security.authentication.LuckyUserDetails;
import com.lucky.core.security.authentication.LuckyUserDetailsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.HashSet;
import java.util.Set;


public class OpenIdAuthenticationProvider extends DaoAuthenticationProvider {

    private LuckyUserDetailsService luckyUserDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    /**
     * 认证逻辑
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken authToken = (OpenIdAuthenticationToken) authentication;
        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add((String) authToken.getPrincipal());
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authToken.getProviderId(), providerUserIds);
        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {

            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        String userId = userIds.iterator().next();

        LuckyUserDetails luckyUserDetails = luckyUserDetailsService.loadUserByUserId(userId);
        if (luckyUserDetails == null) {

            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        OpenIdAuthenticationToken authenticationToken = new OpenIdAuthenticationToken(luckyUserDetails, luckyUserDetails.getAuthorities());
        authenticationToken.setDetails(authToken.getDetails());
        return authenticationToken;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setLuckyUserDetailsService(LuckyUserDetailsService luckyUserDetailsService) {
        this.luckyUserDetailsService = luckyUserDetailsService;
    }

    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }
}
