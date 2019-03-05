/**
 * 
 */
package com.lucky.core.security.authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 扩写{@link UserDetailsService }
 */
public interface LuckyUserDetailsService extends SocialUserDetailsService,UserDetailsService {

	/**
	 * 支持手机号认证
	 * 
	 * @param phoneNumber
	 * @return
	 * @throws UsernameNotFoundException
	 */
	LuckyUserDetails loadUserByMobile(String phoneNumber) throws UsernameNotFoundException;

	/**
	 *  常规表单登录认证
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	LuckyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 *  社交登录认证
	 * @param userId
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	LuckyUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException;
}
