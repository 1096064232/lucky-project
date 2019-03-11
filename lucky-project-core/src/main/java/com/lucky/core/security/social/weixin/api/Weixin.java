/**
 * 
 */
package com.lucky.core.security.social.weixin.api;

/**
 * 微信API调用接口
 */
public interface Weixin {

	/**
	 *  拉取用户信息
	 * @param openId
	 * @return
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}
