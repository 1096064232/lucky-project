/**
 * 
 */
package com.lucky.core.security.verification;


import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	SimpleResponse create(ServletWebRequest request) throws ValidateCodeException;

	/**
	 * 发送校验码
	 *
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	SimpleResponse send(ServletWebRequest request, ValidateCode validateCode) throws ValidateCodeException;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest)throws ValidateCodeException;

}
