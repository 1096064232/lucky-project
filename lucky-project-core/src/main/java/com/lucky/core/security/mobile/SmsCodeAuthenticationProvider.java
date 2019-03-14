/**
 * 
 */
package com.lucky.core.security.mobile;

import com.lucky.core.security.authentication.LuckyUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *  {@link SmsCodeAuthenticationFilter}组装完token后，会传给{@see AuthenticationManager} 
 *  AuthenticationManager会循环便利系统中所有的AuthenticationProvider以此来判断哪个AuthenticationProvider支持
 *        支持认证当前token 
 * @author ouyang
 *
 * @version 1.0
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private LuckyUserDetailsService luckyUserDetailsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		
		UserDetails user = luckyUserDetailsService.loadUserByMobile((String) authenticationToken.getPrincipal());

		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	/*
	 * 判断当前AuthenticationProvider是否支持传进来 的token,要是支持就调用该AuthenticationProvider
	 * 完成token的检验认证
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}


	public void setLuckyUserDetailsService(LuckyUserDetailsService luckyUserDetailsService) {
		this.luckyUserDetailsService = luckyUserDetailsService;
	}

	
}
