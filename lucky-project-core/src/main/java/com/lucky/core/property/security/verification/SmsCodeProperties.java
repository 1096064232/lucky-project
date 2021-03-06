/**
 * 
 */
package com.lucky.core.property.security.verification;

import java.util.Map;

/**
 * 短信验证码的配置项
 */
public class SmsCodeProperties {

	/**
	 *  验证码长度
	 */
	private int length = 6;

	/**
	 * 验证码过期时间
	 */
	private int expireIn = 60;
	
	/**
	 *  需要校验校验码的url，以，分割
	 */
	private  Map<String,String> validateUrl;

	public Map<String, String> getValidateUrl() {
		return validateUrl;
	}

	public void setValidateUrl(Map<String, String> validateUrl) {
		this.validateUrl = validateUrl;
	}

	private AliSmsCodeProperties aliSmsCode = new AliSmsCodeProperties();

	public AliSmsCodeProperties getAliSmsCode() {
		return aliSmsCode;
	}

	public void setAliSmsCode(AliSmsCodeProperties aliSmsCode) {
		this.aliSmsCode = aliSmsCode;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int lenght) {
		this.length = lenght;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

}
