/**
 * 
 */
package com.lucky.core.property.security.verification;

import com.lucky.core.property.security.verification.ImageCodeProperties;
import com.lucky.core.property.security.verification.SmsCodeProperties;

/**
 *  验证码配置项
 */
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image; 
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}
	
}
