package com.lucky.app.security.authentication;

import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.ValidateCodeRepository;
import com.lucky.core.security.verification.ValidateCodeTypeEnum;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

public class InMemoryValidateCodeRepository implements ValidateCodeRepository {

    private MultiValueMap<String, ValidateCode> connections;

    private String identificationParameterName = "deviceId";

    public InMemoryValidateCodeRepository() {
        this.connections = new LinkedMultiValueMap();
    }

    @Override
    public void save(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum, ValidateCode validateCode) {
        connections.add(getKey(request,validateCodeTypeEnum),validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum) {
        try {
            return  connections.get(getKey(request,validateCodeTypeEnum)).get(0);
        } catch (NullPointerException e) {
           return null;
        }
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum) {
        connections.remove(getKey(request,validateCodeTypeEnum));
    }

    public void setIdentificationParameterName(String identificationParameterName) {
        this.identificationParameterName = identificationParameterName;
    }

    private String getKey(ServletWebRequest request,ValidateCodeTypeEnum validateCodeTypeEnum){
        try {
            return ServletRequestUtils.getRequiredStringParameter(request.getRequest(),this.identificationParameterName)+validateCodeTypeEnum.toString().toUpperCase();
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("未找到客户端的唯一标识的参数");
        }
    }
}
