/**
 * 
 */
package com.lucky.core.security.authentication;

import org.springframework.social.security.SocialUserDetails;

/**
 * 
 *  扩写SocialUserDetails加入手机号
 * @author lucky ouyang
 *
 * @version 1.0
 */
public interface LuckyUserDetails extends SocialUserDetails {

	/**
	 *  获取手机号
	 * @return
	 */
	String getMobileNumber();
}
