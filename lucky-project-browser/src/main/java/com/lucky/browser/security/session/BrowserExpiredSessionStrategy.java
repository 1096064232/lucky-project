/**
 * 
 */
package com.lucky.browser.security.session;

import java.io.IOException;
import javax.servlet.ServletException;

import com.lucky.core.property.LuckyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 *  Session失效处理接口
 */
public class BrowserExpiredSessionStrategy implements SessionInformationExpiredStrategy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LuckyProperties luckyProperties;

	/**
	 * 重定向策略
	 */
	@Autowired
	RedirectStrategy redirectStrategy;

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		logger.debug("并发登录，导致session失效,跳转到登录页面上去");
		event.getRequest().getSession();
		redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), luckyProperties.getSecurity().getAuthentication().getBrowser().getLoginPage());
	}
}
