/**
 *
 */
package com.lucky.core.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.verification.ValidateCodeProcessorHolder;
import com.lucky.core.security.verification.ValidateCodeTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 系统配置信息
     */
    @Autowired
    private LuckyProperties luckyProperties;
    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        //用户配置需要拦截的图形验证码
        addUrlToMap(luckyProperties.getSecurity().getCode().getImage().getValidateUrl(), ValidateCodeTypeEnum.IMAGE);
        addUrlToMap(luckyProperties.getSecurity().getAuthentication().getFormLogin().getLoginProcessingUrl(), ValidateCodeTypeEnum.IMAGE);
        //用户配置需要拦截的短信验证码
        addUrlToMap(luckyProperties.getSecurity().getCode().getSms().getValidateUrl(), ValidateCodeTypeEnum.SMS);
        addUrlToMap(luckyProperties.getSecurity().getAuthentication().getMobileLogin().getLoginProcessingUrl(), ValidateCodeTypeEnum.SMS);
    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeTypeEnum type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                logger.debug("初始化ValidateCodeFilter，需要校验的验证码类型是：{}，校验的url是:{}",type.toString().toLowerCase(),url);
                urlMap.put(url, type);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        ValidateCodeTypeEnum type = getValidateCodeType(request);
        if (type != null) {
            logger.debug("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
                logger.debug("验证码校验通过");
            } catch (ValidateCodeException exception) {
                logger.debug("验证码校验不通过，调用认证失败处理器");
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationException(exception.getMessage(),exception) {});
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeTypeEnum getValidateCodeType(HttpServletRequest request) {
        String targetUrl = request.getRequestURI();
        logger.debug("当前拦截的请求是:{}",targetUrl);
        Set<String> urls = urlMap.keySet();
        for (String url : urls) {
            if (pathMatcher.match(url, targetUrl)) {
               return urlMap.get(url);
            }
        }
        return null;
    }

}
