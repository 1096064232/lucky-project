/**
 * 
 */
package com.lucky.core.security.verification.sms;

import com.lucky.core.property.LuckyProperties;
import com.lucky.core.property.security.verification.SmsCodeProperties;
import com.lucky.core.property.security.verification.ValidateCodeParameterProperties;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 *  短信验证码生成器
 */
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private LuckyProperties luckyProperties;

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		SmsCodeProperties smsCodeProperties = luckyProperties.getSecurity().getCode().getSms();
		ValidateCodeParameterProperties parameterProperties  = luckyProperties.getSecurity().getParameter().getCode();
		int length = ServletRequestUtils.getIntParameter(request.getRequest(), parameterProperties.getSmsLengthParameterName(),
				smsCodeProperties.getLength());
		int expireIn=ServletRequestUtils.getIntParameter(request.getRequest(), parameterProperties.getImageExpireInParameterName(),
				smsCodeProperties.getExpireIn());
		String code = RandomStringUtils.randomNumeric(length);
		return new ValidateCode(code, expireIn);
	}

}
