/**
 * 
 */
package com.lucky.core.security.verification.impl;

import java.util.Map;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.property.LuckyProperties;
import com.lucky.core.security.verification.*;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.lucky.core.exception.ValidateCodeException;


public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {


	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;
	
	@Autowired
	private ValidateCodeRepository validateCodeRepository;

	@Autowired
	private LuckyProperties luckyProperties;

	@Override
	public SimpleResponse create(ServletWebRequest request) throws ValidateCodeException {
		//生成校验码
		C validateCode = generate(request);
		//保存校验码
		save(request, validateCode);
		return  new SimpleResponse(validateCode);
	}

	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).getParamNameOnValidate();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 * 
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) throws ValidateCodeException {
		validateCodeRepository.save(request, getValidateCodeType(request), validateCode);
	}

	/**
	 * 根据请求的url获取校验码的类型
	 * 
	 * @param request
	 * @return
	 */
	private ValidateCodeTypeEnum getValidateCodeType(ServletWebRequest request) {
		//获取当前实现类的类名的前缀
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
		//根据类型前缀构建验证码类型
		return ValidateCodeTypeEnum.valueOf(type.toUpperCase());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeTypeEnum processorType = getValidateCodeType(request);
		C codeInRepository =  (C) validateCodeRepository.get(request, processorType);
		//获取当前校验码的类型
		ValidateCodeTypeEnum validateCodeTypeEnum = getValidateCodeType(request);
		String codeParameterName="";
		if(validateCodeTypeEnum == ValidateCodeTypeEnum.SMS){
			codeParameterName = luckyProperties.getSecurity().getParameter().getCode().getSmsCodeParameterName();
		}else if (validateCodeTypeEnum == ValidateCodeTypeEnum.IMAGE){
			codeParameterName = luckyProperties.getSecurity().getParameter().getCode().getImageCodeParameterName();
		}else {
			throw new ValidateCodeException("未知的验证码类型");
		}
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					codeParameterName);
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInRepository == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInRepository.isExpried()) {
			validateCodeRepository.remove(request, processorType);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}
		if (!StringUtils.equals(codeInRepository.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}
		validateCodeRepository.remove(request, processorType);
	}

}
