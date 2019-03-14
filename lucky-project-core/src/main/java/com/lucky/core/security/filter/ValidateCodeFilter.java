/**
 *
 */
package com.lucky.core.security.filter;

import java.io.IOException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lucky.common.util.CommonUtils;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.verification.ValidateCodeProcessorHolder;
import com.lucky.core.security.verification.ValidateCodeTypeEnum;
import org.apache.commons.collections.MapUtils;
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
     * 验证请求url与配置的url是否匹配的工具类
     */
    @Autowired
    private AntPathMatcher pathMatcher;

    /**
     * 存放所有需要校验验证码的url与之对应的校验码类型
     */
    private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();

    /**
     * 存放所有需要校验验证码的url与之对应的请求类型
     */
    private Map<String, Set<String>> methondMap = new HashMap<>();


    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        //将需要校验校验码的url与请求方式对应起来
        addMethondMap(luckyProperties.getSecurity().getCode().getImage().getValidateUrl());
        addMethondMap(luckyProperties.getSecurity().getCode().getSms().getValidateUrl());

        //将需要校验校验码的url与校验码的类型对应起来
        addUrlToMap(luckyProperties.getSecurity().getCode().getImage().getValidateUrl(), ValidateCodeTypeEnum.IMAGE);
        addUrlToMap(luckyProperties.getSecurity().getCode().getSms().getValidateUrl(), ValidateCodeTypeEnum.SMS);

        logger.debug("methondMap的值是:");
        for(String key:methondMap.keySet()){
           Set<String> set =  methondMap.get(key);
           for (String url:set){
               logger.debug("key is:{},value is:{}",key,url);
           }
        }
        logger.debug("urlMap的值是:");
        for (String url:urlMap.keySet()){
            logger.debug("key is:{},value is:{}",url,urlMap.get(url));
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
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationException(exception.getMessage(), exception){});
                return;
            }
        }
        chain.doFilter(request, response);
    }


    /**
     *  将url与对应的请求方式放在一起
     * @param validateUrl
     */
    private void addMethondMap(Map<String, String> validateUrl) {
        if (MapUtils.isNotEmpty(validateUrl)) {
            String[] urls;
            Set<String> usrlSet = new HashSet<>();
            for (String method:validateUrl.keySet()){
                urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(validateUrl.get(method), ",");
                Collections.addAll(usrlSet,urls);
                if(methondMap.containsKey(method)){
                    Set<String> hasUrl = methondMap.get(method);
                    hasUrl.addAll(usrlSet);
                    methondMap.put(method.toLowerCase(),hasUrl);
                }else {
                    methondMap.put(method.toLowerCase(),usrlSet);
                }
            }
        }
    }


    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param validateUrl
     * @param type
     */
    protected void addUrlToMap(Map<String, String> validateUrl, ValidateCodeTypeEnum type) {

        if (MapUtils.isNotEmpty(validateUrl)) {
            String[] urls;
            Collection<String> urlList = validateUrl.values();
            Iterator<String> iterator = urlList.iterator();
            while (iterator.hasNext()) {
                urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(iterator.next(), ",");
                for (String url : urls) {
                    urlMap.put(url, type);
                }
            }
        }
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeTypeEnum getValidateCodeType(HttpServletRequest request) {
        String requestMethond = request.getMethod().toLowerCase();
        logger.debug("当前请求方式是：{}，请求的url是:{}",requestMethond,request.getRequestURI());
        if(methondMap.containsKey(requestMethond)){
            Set<String> urls = methondMap.get(requestMethond);
            for (String url : urls) {
                logger.debug("当前请求方式是：{}，该请求方式下需要校验的url有:{}",requestMethond,url);
                if (pathMatcher.match(url, request.getRequestURI())) {
                    return urlMap.get(url);
                }
            }
        }
        return null;
    }

}
