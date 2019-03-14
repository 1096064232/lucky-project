package com.lucky.demo.app.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *  根据社交用户信息默认创建一个用户并返回一个标识
     * @param connection
     * @return
     */
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建一个用户并返回一个标识
        logger.debug("根据社交用户信息默认创建一个用户并返回一个标识：{}",connection.getDisplayName());
        return connection.getDisplayName();
    }
}
