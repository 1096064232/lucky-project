/**
 * 
 */
package com.lucky.core.security.social.wxpublic.api;

/**
 * 微信公众号API调用接口
 */
public interface WeixinPublic {

	/**
	 *  拉取用户信息(需scope为 snsapi_userinfo)
	 * @param openId
	 * @return
	 */
	WeixinPublicUserInfo getUserInfo(String openId);
	
}
