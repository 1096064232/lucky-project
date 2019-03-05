/**
 *
 */
package com.lucky.core.security.verification.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.constant.ValidateCodeConstant;
import com.lucky.core.property.security.verification.AliSmsCodeProperties;
import com.lucky.core.security.filter.ValidateCodeFilter;
import com.lucky.core.security.verification.ValidateCodeGenerator;
import com.lucky.core.security.verification.ValidateCodeProcessor;
import com.lucky.core.security.verification.ValidateCodeProcessorHolder;
import com.lucky.core.security.verification.image.ImageValidateCodeGenerator;
import com.lucky.core.security.verification.image.ImageValidateCodeProcessor;
import com.lucky.core.security.verification.sms.SmsValidateCodeGenerator;
import com.lucky.core.security.verification.sms.SmsValidateCodeProcessor;
import com.lucky.core.security.verification.sms.SmsValidateCodeSender;
import com.lucky.core.security.verification.sms.impl.AliSmsValidateCodeSender;
import com.lucky.core.security.verification.sms.impl.DefaultSmsValidateCodeSender;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LuckyProperties luckyProperties;

    /**
     * 默认的验证码处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeProcessorHolder.class)
    public ValidateCodeProcessorHolder validateCodeProcessorHolder() {
        logger.debug("生成系统默认的验证码处理支持器ValidateCodeProcessorHolder的对象");
        return new ValidateCodeProcessorHolder();
    }

    /**
     * 默认的图形验证码处理器
     *
     * @return
     */
    @Bean(name = ValidateCodeConstant.IMAGE_CODE + "ValidateCodeProcessor")
    @ConditionalOnMissingBean(name = ValidateCodeConstant.IMAGE_CODE + "ValidateCodeProcessor")
    public ValidateCodeProcessor imageValidateCodeProcessor() {
        logger.debug("生成系统默认的图形验证码处理器ImageValidateCodeProcessor的对象");
        return new ImageValidateCodeProcessor();
    }

    /**
     * 默认的验证码生成器
     *
     * @return
     */
    @Bean(name = ValidateCodeConstant.IMAGE_CODE + "ValidateCodeGenerator")
    @ConditionalOnMissingBean(name = ValidateCodeConstant.IMAGE_CODE + "ValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        logger.debug("生成系统默认的图形验证码生成器ImageValidateCodeGenerator的对象");
        return new ImageValidateCodeGenerator();
    }

    /**
     * 默认的手机短信验证码处理器
     *
     * @return
     */
    @Bean(name = ValidateCodeConstant.SMS_CODE + "ValidateCodeProcessor")
    @ConditionalOnMissingBean(name = ValidateCodeConstant.SMS_CODE + "ValidateCodeProcessor")
    public ValidateCodeProcessor smsCodeValidateCodeProcessor() {
        logger.debug("生成系统默认的短信验证码处理器SmsValidateCodeProcessor的对象");
        return new SmsValidateCodeProcessor();
    }

    /**
     * 默认的手机短信验证码生成器
     *
     * @return
     */
    @Bean(name = ValidateCodeConstant.SMS_CODE + "ValidateCodeGenerator")
    @ConditionalOnMissingBean(name = ValidateCodeConstant.SMS_CODE + "ValidateCodeGenerator")
    public ValidateCodeGenerator smsCodeValidateCodeGenerator() {
        logger.debug("生成系统默认的短信验证码生成器SmsValidateCodeGenerator的对象");
        return new SmsValidateCodeGenerator();
    }


    /**
     * 配置阿里云短信服务客户端
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(IAcsClient.class)
    public IAcsClient iAcsClient() {
        AliSmsCodeProperties aliSmsCodeProperties = luckyProperties.getSecurity().getCode().getSms().getAliSmsCode();
        if (StringUtils.isBlank(aliSmsCodeProperties.getAccessKeyId())) {
            logger.debug("系统中没有阿里云短信服务请求客户端IAcsClient，但用户没有配置相应的AccessKeyId，因此返回null");
            return null;
        }
        DefaultProfile profile = DefaultProfile.getProfile(aliSmsCodeProperties.getRegionId(),aliSmsCodeProperties.getAccessKeyId(),aliSmsCodeProperties.getAccessSecret());
        logger.debug("系统中没有阿里云短信服务请求客户端IAcsClient，用户有配置相应的AccessKeyId，因此返回DefaultAcsClientd的对象");
        return new DefaultAcsClient(profile);
    }

    /**
     * 默认的短信服务发送器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsValidateCodeSender.class)
    public SmsValidateCodeSender smsValidateCodeSender() {
        if (StringUtils.isNotBlank(luckyProperties.getSecurity().getCode().getSms().getAliSmsCode().getAccessKeyId())) {
            //若配置了阿里云则使用阿里云的短信服务
            logger.debug("系统中用户没有配置相应的短信服务发送器对象，而且用户配置了阿里云短信服务的AccessKeyId，因此返回AliSmsValidateCodeSender的对象");
            return new AliSmsValidateCodeSender();
        }
        logger.debug("系统中用户没有配置相应的短信服务发送器对象，但用户没有配置阿里云短信服务的AccessKeyId，DefaultSmsValidateCodeSender的对象");
        return new DefaultSmsValidateCodeSender();
    }

    /**
     *  配置过滤器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeFilter.class)
    public ValidateCodeFilter validateCodeFilter(){
        return  new ValidateCodeFilter();
    }

}
