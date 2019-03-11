/**
 * 
 */
package com.lucky.core.security.social.qq.connet;

import com.lucky.core.security.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;




/**
 * QQConnectionFactory
 * @author lucky ouyang
 *
 * @version 1.0
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
