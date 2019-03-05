package com.lucky.core.security.authentication;

import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;

/**
 * 会收集系统中所有此接口的实现类并将其配置在Security中
 *  {@see SecurityConfigurer} 
 * @author ouyang
 *
 * @version 1.0
 */
public interface LcukySecurityConfigurer<O, B extends SecurityBuilder<O>> extends  SecurityConfigurer<O, B>{

}
