/**
 * 
 */
package com.lucky.core.security.social.qq.connet;


import com.lucky.core.security.social.qq.api.QQ;
import com.lucky.core.security.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 *  qq服务提供商
 * @author lucky ouyang
 *
 * @version 1.0
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;
	
	/**
	 *  引导用户跳转的地址
	 */
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	/**
	 * 拿授权码获取access_token的地址
	 */
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId; 
	}
	
	/**
	 * 每个用户的qq用户信息都是不一样的，是多例对象
	 */
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
