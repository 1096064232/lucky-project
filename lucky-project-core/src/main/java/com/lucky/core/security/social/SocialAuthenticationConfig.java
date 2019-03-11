/**
 *
 */
package com.lucky.core.security.social;

import com.lucky.core.property.LuckyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author lucky ouyang
 * @version 1.0
 */
@Order(1) //不加此注解，加载得到是QQAutoConfig的getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator)方法
@Configuration
@EnableSocial
public class SocialAuthenticationConfig extends SocialConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    LuckyProperties luckyProperties;

    @Autowired(required = false)
    ConnectionSignUp connectionSignUp;

    /**
     *  为什么不起作用 ？？？
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        UsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        ((JdbcUsersConnectionRepository) connectionRepository).setTablePrefix(luckyProperties.getSecurity().getAuthentication().getSocial().getTablePrefix());
        if (connectionSignUp != null) {
            ((JdbcUsersConnectionRepository) connectionRepository).setConnectionSignUp(connectionSignUp);
        }
        return connectionRepository;
    }


    /**
     * 这个对象将会加入Security中
     *
     * @return
     */
    @Bean
    public SpringSocialConfigurer luckySpringSocialConfigurer() {
        SpringSocialConfigurer springSocialConfigurer = new LuckySpringSocialConfigurer(luckyProperties.getSecurity().getAuthentication().getSocial().getFilterProcessesUrl());
        springSocialConfigurer.signupUrl(luckyProperties.getSecurity().getAuthentication().getSocial().getSignupUrl());
        return springSocialConfigurer;
    }

    /**
     * 使用社交登录，若系统需要用户注册或绑定到一个账号上去，则需要此工具类
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

}
