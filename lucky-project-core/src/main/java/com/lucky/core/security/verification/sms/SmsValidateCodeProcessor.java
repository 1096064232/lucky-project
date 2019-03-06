/**
 * 
 */
package com.lucky.core.security.verification.sms;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码处理器
 */
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsValidateCodeSender smsCodeSender;

	@Autowired
	private LuckyProperties luckyProperties;

	@Override
	public SimpleResponse send(ServletWebRequest request, ValidateCode validateCode) throws ValidateCodeException {

		String mobile = null;
		try {
			mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), luckyProperties.getSecurity().getParameter().getCode().getSmsMobileParameterName());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException(e);
		}
		return smsCodeSender.send(mobile, validateCode.getCode());
	}

}
