/**
 * 
 */
package com.lucky.core.security.social.qq.config;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.auhentication.QQAuthenticationProperties;
import com.lucky.core.security.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionSignUp;

import javax.sql.DataSource;


/**
 * 
 * @author lucky ouyang
 *
 * @version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "lucky.security.authentication.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {


	@Autowired
	private LuckyProperties luckyProperties;

	@Autowired
	DataSource dataSource;

	@Autowired(required = false)
	ConnectionSignUp connectionSignUp;


	/**
	 *  配置QQ链接工厂
	 * @return
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQAuthenticationProperties qqConfig = luckyProperties.getSecurity().getAuthentication().getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
