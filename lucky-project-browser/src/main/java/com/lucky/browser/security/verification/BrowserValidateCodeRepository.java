package com.lucky.browser.security.verification;

import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.ValidateCodeRepository;
import com.lucky.core.security.verification.ValidateCodeTypeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author ouyangfan
 * @Date 2019/3/221:48
 **/
@Component
public class BrowserValidateCodeRepository implements ValidateCodeRepository {
    @Override
    public void save(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum, ValidateCode validateCode) {
        request.getRequest().getSession().setAttribute(validateCodeTypeEnum.getParamNameOnValidate().toLowerCase()+request.getSessionId(),validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum) {
        return (ValidateCode) request.getRequest().getSession().getAttribute(validateCodeTypeEnum.getParamNameOnValidate().toLowerCase()+request.getSessionId());
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeTypeEnum) {
        request.getRequest().getSession().removeAttribute(validateCodeTypeEnum.getParamNameOnValidate().toLowerCase()+request.getSessionId());
    }
}
