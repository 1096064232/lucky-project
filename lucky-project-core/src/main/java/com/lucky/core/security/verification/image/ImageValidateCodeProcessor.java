/**
 *
 */
package com.lucky.core.security.verification.image;

import javax.imageio.ImageIO;

import com.lucky.common.pojo.SimpleResponse;
import com.lucky.core.exception.ValidateCodeException;
import com.lucky.core.security.verification.ValidateCode;
import com.lucky.core.security.verification.impl.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;


/**
 * 图片验证码处理器
 */
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    public SimpleResponse send(ServletWebRequest request, ValidateCode imageCode) throws ValidateCodeException {
        try {
            ImageIO.write(((ImageValidateCode) imageCode).getImage(), "JPEG", request.getResponse().getOutputStream());
            return  new SimpleResponse("success");
        } catch (IOException e) {
            throw new ValidateCodeException(e);
        }
    }
}
