/**
 * 
 */
package com.lucky.core.security.social.weixin.config;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.auhentication.WeixinAuthenticationProperties;
import com.lucky.core.security.social.weixin.connect.WeixinConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionSignUp;
import javax.sql.DataSource;


/**
 * 微信登录配置
 * 
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "lucky.security.authentication.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LuckyProperties luckyProperties;

	@Autowired
	DataSource dataSource;

	@Autowired(required = false)
	ConnectionSignUp connectionSignUp;

	/**
	 *  微信链接工厂
	 * @return
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {

		WeixinAuthenticationProperties weixinConfig = luckyProperties.getSecurity().getAuthentication().getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}

}
