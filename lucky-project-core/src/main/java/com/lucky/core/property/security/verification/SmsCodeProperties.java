/**
 * 
 */
package com.lucky.core.property.security.verification;

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
	private String validateUrl;

	private AliSmsCodeProperties aliSmsCode = new AliSmsCodeProperties();

	public AliSmsCodeProperties getAliSmsCode() {
		return aliSmsCode;
	}

	public void setAliSmsCode(AliSmsCodeProperties aliSmsCode) {
		this.aliSmsCode = aliSmsCode;
	}

	public String getValidateUrl() {
		return validateUrl;
	}

	public void setValidateUrl(String validateUrl) {
		this.validateUrl = validateUrl;
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
