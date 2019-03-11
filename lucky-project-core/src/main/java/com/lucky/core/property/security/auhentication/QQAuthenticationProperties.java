/**
 * 
 */
package com.lucky.core.property.security.auhentication;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author zhailiang
 *
 */
public class QQAuthenticationProperties extends SocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
