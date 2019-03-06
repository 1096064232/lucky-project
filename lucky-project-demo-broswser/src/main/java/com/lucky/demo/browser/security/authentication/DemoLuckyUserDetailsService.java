package com.lucky.demo.browser.security.authentication;

import com.lucky.core.exception.PhoneNumberNotFoundException;
import com.lucky.core.exception.UserIdNotFoundException;
import com.lucky.core.security.authentication.LuckyUser;
import com.lucky.core.security.authentication.LuckyUserDetails;
import com.lucky.core.security.authentication.LuckyUserDetailsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoLuckyUserDetailsService implements LuckyUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String USER_NAME = "ouyangfan";

    private static final String PASS_WORD = "123456";

    private static final String MOBILE_NUMBER = "18258193155";

    private static final String USER_ID = "userId-01";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LuckyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("当前认证方式是用户名+密码，用户名：{}", username);
        if (StringUtils.equals(USER_NAME, username)) {
            return getLuckyUser();
        }
        throw new UsernameNotFoundException("用户名不存在");
    }

    @Override
    public LuckyUserDetails loadUserByMobile(String phoneNumber) throws PhoneNumberNotFoundException {
        logger.debug("当前认证方式是手机号+短信验证码，手机号：{}", phoneNumber);
        if (StringUtils.equals(MOBILE_NUMBER, phoneNumber)) {
            return getLuckyUser();
        }
        throw new PhoneNumberNotFoundException("手机号不存在");
    }

    @Override
    public LuckyUserDetails loadUserByUserId(String userId) throws UserIdNotFoundException {
        logger.debug("当前认证方式是社交登录，userId：{}", userId);
        if (StringUtils.equals(USER_ID, userId)) {
            return getLuckyUser();
        }
        throw new PhoneNumberNotFoundException("userId不存在");
    }

    private LuckyUserDetails getLuckyUser() {

        return LuckyUser
                .builder()
                .username(USER_NAME)
                .password(passwordEncoder.encode(PASS_WORD))
                .mobileNumber(MOBILE_NUMBER)
                .userId(USER_ID)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .authorities("ADMIN", "SUPER_ADMIN").build();
    }
}
