package com.lucky.demo.browser.security.authentication;

import com.lucky.core.security.authentication.LuckyUserDetails;
import com.lucky.core.security.authentication.LuckyUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DemoLuckyUserDetailsService implements LuckyUserDetailsService {
    @Override
    public LuckyUserDetails loadUserByMobile(String phoneNumber) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public LuckyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public LuckyUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return null;
    }
}
