package com.lucky.demo.browser.security.authentication;

import com.lucky.common.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ouyangfan
 * @Date 2019/3/314:09
 **/
@Component
public class DemoAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("当前认证失败的ur是:{},认证失败异常信息是:{}",request.getRequestURI(), CommonUtils.toString(exception));
        super.onAuthenticationFailure(request, response, exception);
    }
}
