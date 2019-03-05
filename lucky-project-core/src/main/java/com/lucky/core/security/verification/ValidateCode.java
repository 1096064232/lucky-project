/**
 * 
 */
package com.lucky.core.security.verification;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 校验码
 */
public class ValidateCode implements Serializable{

	private static final long serialVersionUID = 4604852924350386065L;

	/**
	 *  校验码内容
	 */
	private String code;

	/**
	 *  校验码过期时间点
	 */
	private LocalDateTime expireTime;
	
	public ValidateCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}

	/**
	 *  是否已经过期
	 * @return
	 */
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	
}
