/**
 * 
 */
package com.lucky.core.security.verification.sms;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;

/**
 * 短信验证码发送器
 */
public interface SmsValidateCodeSender {

	SimpleResponse send(String mobile, String code) throws ValidateCodeException;

}
