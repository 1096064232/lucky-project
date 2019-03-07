/**
 * 
 */
package com.lucky.browser.security.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lucky.core.property.LuckyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 *  Session过期处理逻辑
 */
public class BrowserInvalidSessionStrategy  implements InvalidSessionStrategy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LuckyProperties luckyProperties;

	/**
	 * 重定向策略
	 */
	@Autowired
	RedirectStrategy redirectStrategy;

	@Override
	public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
		logger.debug("session失效了跳珠到登录页面上去");
		httpServletRequest.getSession();
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, luckyProperties.getSecurity().getAuthentication().getBrowser().getLoginPage());
	}
}
