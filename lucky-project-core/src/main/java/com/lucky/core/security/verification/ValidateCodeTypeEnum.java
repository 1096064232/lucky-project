/**
 * 
 */
package com.lucky.core.security.verification;


import com.lucky.core.property.constant.ValidateCodeConstant;

/**
 *  校验码的枚举类型
 * @author lucky ouyang
 *
 * @version 1.0
 */
public enum ValidateCodeTypeEnum {
	
	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return ValidateCodeConstant.SMS_CODE;
		}
	},
	/**
	 * 图片验证码
	 */
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return ValidateCodeConstant.IMAGE_CODE;
		}
	};

	/**
	 * 校验时从请求中获取的参数的名字
	 * @return
	 */
	public abstract String getParamNameOnValidate();

}
