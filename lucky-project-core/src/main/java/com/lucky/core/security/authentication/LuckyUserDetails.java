/**
 * 
 */
package com.lucky.core.security.authentication;

import org.springframework.social.security.SocialUserDetails;

/**
 * 
 *  扩写SocialUserDetails加入ID,以便在{@see EntityListener}
 * @author lucky ouyang
 *
 * @version 1.0
 */
public interface LuckyUserDetails extends SocialUserDetails {

	/**
	 *  获取用户主键
	 * @return
	 */
	Long getId();
}
