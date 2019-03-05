/**
 * 
 */
package com.lucky.core.security.verification;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucky.core.exception.ValidateCodeException;


/**
 * 校验码处理器
 */
public class ValidateCodeProcessorHolder {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;


	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeTypeEnum type) {
		return findValidateCodeProcessor(type.getParamNameOnValidate());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		logger.debug("系统中ValidateCodeProcessor的实现类的对象有：");
		for (String key:validateCodeProcessors.keySet()){
			logger.debug("key is :{},value is :{}",key,validateCodeProcessors.get(key));
		}
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}
