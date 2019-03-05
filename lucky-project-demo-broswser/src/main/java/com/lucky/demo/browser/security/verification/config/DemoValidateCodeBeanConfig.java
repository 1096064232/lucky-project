package com.lucky.demo.browser.security.verification.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.verification.AliSmsCodeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ouyangfan
 * @Date 2019/3/223:41
 **/
@Configuration
public class DemoValidateCodeBeanConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LuckyProperties luckyProperties;

//    @Bean
    public IAcsClient getIAcsClient(){
        AliSmsCodeProperties aliSmsCodeProperties = luckyProperties.getSecurity().getCode().getSms().getAliSmsCode();
        DefaultProfile profile = DefaultProfile.getProfile(aliSmsCodeProperties.getRegionId(),aliSmsCodeProperties.getAccessKeyId(),aliSmsCodeProperties.getAccessSecret());
        logger.debug("这是demo的IAcsClient");
        return new DefaultAcsClient(profile);
    }
}
