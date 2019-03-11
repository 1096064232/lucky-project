/**
 * 
 */
package com.lucky.core.security.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.core.exception.SocialException;

import java.nio.charset.Charset;
import java.util.List;

/**
 *  调用所有OpenAPI时，除了各接口私有的参数外，所有OpenAPI都需要传入基于OAuth2.0协议的通用参数：
 *   access_token	  可通过使用Authorization_Code获取Access_Token 或来获取。  access_token有3个月有效期。
 *   oauth_consumer_key	     申请QQ登录成功后，分配给应用的appid
 *   openid	用户的ID，与QQ号码一一对应。      可通过调用https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN 来获取。
 *   
 * @author lucky ouyang
 *
 * @version 1.0
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 拿token换取openid的接口
	 */
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	/**
	 * 拿openid换取用户信息的接口
	 */
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	private String appId;

	private String openId;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 *   在初始化时拿access_token换取openid,qq文档要求是把accessToken放在请求的url中，
	 *   因此指定的token策略是TokenStrategy.ACCESS_TOKEN_PARAMETER，
	 *   TokenStrategy.AUTHORIZATION_HEADER是把accessToken放在请求头里
	 * @param accessToken
	 * @param appId
	 */
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}

	/**
	 *  获取用户信息
	 * @return
	 */
	@Override
	public QQUserInfo getUserInfo() {

		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);
		try {
			QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
			if(StringUtils.isNotBlank(userInfo.getMsg())){
				logger.error("openid换取QQUserInfo返回错误的JSON数据包:{}",result );
				throw new SocialException("获取用户信息失败:"+userInfo.getMsg());
			}
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			logger.error("openid换取QQUserInfo失败:{}",e.getMessage() );
			throw new SocialException("获取用户信息失败", e);
		}
	}

}
