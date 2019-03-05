/**
 * 
 */
package com.lucky.core.security.mobile;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.auhentication.MobileLoginAuthenticationProperties;
import com.lucky.core.security.authentication.LcukySecurityConfigurer;
import com.lucky.core.security.authentication.LuckyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 *  手机号+手机验证码登录
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
		extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
		implements LcukySecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationSuccessHandler aAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private LuckyUserDetailsService luckyUserDetailsService;
	
	@Autowired
	private LuckyProperties luckyProperties;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//配置过滤器
		MobileLoginAuthenticationProperties mobileLoginAuthentication = luckyProperties.getSecurity().getAuthentication().getMobileLogin();
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter(mobileLoginAuthentication.getLoginProcessingUrl(),mobileLoginAuthentication.getHttpMethod(),luckyProperties.getSecurity().getParameter().getMobileLogin().getMobileParameterName());
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(aAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		// 判断AuthenticationManager是否为null
		smsCodeAuthenticationFilter.afterPropertiesSet();
		//配置Provider
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setLuckyUserDetailsService(luckyUserDetailsService);
		//加入到Security配置中
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
}
