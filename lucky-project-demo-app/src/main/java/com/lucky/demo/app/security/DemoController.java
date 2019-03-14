package com.lucky.demo.app.security;

import com.lucky.common.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderSignInUtils providerSignInUtils;

    @PostMapping("/user/regist")
    public void  register(HttpServletRequest request){
        Connection<?> connection =providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        logger.debug("当前注册/绑定的社交用户是:{}", CommonUtils.toString(connection));
        providerSignInUtils.doPostSignUp(connection.getDisplayName(),new ServletWebRequest(request));
    }
}
