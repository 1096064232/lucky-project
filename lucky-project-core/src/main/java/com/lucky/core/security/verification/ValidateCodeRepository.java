/**
 * 
 */
package com.lucky.core.security.verification;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *  验证码的存储接口
 */
public interface ValidateCodeRepository {

	/**
	 * 保存校验码
	 * 
	 * @param request
	 * @param validateCodeTypeEnum
	 * @param validateCode
	 */
	void save(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum, ValidateCode validateCode);

	/**
	 * 获取校验码
	 * 
	 * @param request
	 * @param validateCodeTypeEnum
	 * @return
	 */
	ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum);

	/**
	 * 移除校验码
	 * 
	 * @param request
	 * @param validateCodeTypeEnum
	 */
	void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum);
}
