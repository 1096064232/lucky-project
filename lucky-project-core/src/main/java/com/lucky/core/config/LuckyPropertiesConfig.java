package com.lucky.core.config;

import com.lucky.core.property.LuckyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LuckyProperties.class)
public class LuckyPropertiesConfig {
}
