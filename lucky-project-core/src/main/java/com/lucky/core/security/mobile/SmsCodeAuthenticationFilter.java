/**
 * 
 */
package com.lucky.core.security.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 *  手机短信认证的过滤器，拦截认证请求，组装token完成认证流程
 * @author ouyang
 *
 * @version 1.0
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	/**
	 *  手机号参数名称
	 */
	private String mobileParameterName;

	/**
	 * 手机短信登录提交方式
	 */
	private String httpMethod;


	/**
	 *  在初始化该过滤器时，需要传入提交手机短信登录方式的url,和Http方法
	 * @param loginProcessingUrl url
	 * @param httpMethod Http方法
	 * @param mobileParameterName 手机号参数名称
	 */
	public SmsCodeAuthenticationFilter(String loginProcessingUrl,String httpMethod,String mobileParameterName) {
		super(new AntPathRequestMatcher(loginProcessingUrl, httpMethod.toUpperCase()));
		this.httpMethod=httpMethod;
		this.mobileParameterName = mobileParameterName;
	}
	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equalsIgnoreCase(httpMethod)) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String mobile = obtainMobile(request);
		if (mobile == null) {
			mobile = "";
		}
		mobile = mobile.trim();
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 从请求中获取获取手机号
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameterName);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request     that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its
	 *                    details set
	 */
	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
}
