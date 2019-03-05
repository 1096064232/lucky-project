package com.lucky.core.property.security.verification;

import com.lucky.core.security.verification.ValidateCodeTypeEnum;

/**
 *  验证码参数名称配置
 * @Author ouyangfan
 * @Date 2019/3/220:16
 **/
public class ValidateCodeParameterProperties {

    /**
     *  图形验证码 -- 请求中宽度的参数名称
     */
    private String imageWidthParameterName = "width";

    /**
     *  图形验证码 -- 请求中高度的参数名称
     */
    private String imageHeightParameterName = "height";

    /**
     *  图形验证码 -- 请求的长度的参数名称
     */
    private String imageLengthParameterName = "length";

    /**
     *  图形验证码 -- 请求中过期时间的参数名称
     */
    private String imageExpireInParameterName = "expireIn";

    /**
     *  图形验证码 -- 请求中验证码类型的参数名称
     */
    private String imageTypeParameterName = "type";

    /**
     *  短信验证码 -- 请求中长度的参数名称
     */
    private String smsLengthParameterName = "length";

    /**
     *  短信验证码 -- 请求中过期时间的参数名称
     */
    private String smsExpireInParameterName = "expireIn";

    /**
     *  短信验证码 -- 请求中手机号的参数名称
     */
    private String smsMobileParameterName = "mobile";

    /**
     *  图形验证码的参数名称
     */
    private String imageCodeParameterName = ValidateCodeTypeEnum.IMAGE.getParamNameOnValidate();
    /**
     *  短信验证码的参数名
     */
    private String smsCodeParameterName = ValidateCodeTypeEnum.SMS.getParamNameOnValidate();;

    public String getImageCodeParameterName() {
        return imageCodeParameterName;
    }

    public void setImageCodeParameterName(String imageCodeParameterName) {
        this.imageCodeParameterName = imageCodeParameterName;
    }

    public String getSmsCodeParameterName() {
        return smsCodeParameterName;
    }

    public void setSmsCodeParameterName(String smsCodeParameterName) {
        this.smsCodeParameterName = smsCodeParameterName;
    }

    public String getSmsLengthParameterName() {
        return smsLengthParameterName;
    }

    public void setSmsLengthParameterName(String smsLengthParameterName) {
        this.smsLengthParameterName = smsLengthParameterName;
    }

    public String getSmsExpireInParameterName() {
        return smsExpireInParameterName;
    }

    public void setSmsExpireInParameterName(String smsExpireInParameterName) {
        this.smsExpireInParameterName = smsExpireInParameterName;
    }

    public String getSmsMobileParameterName() {
        return smsMobileParameterName;
    }

    public void setSmsMobileParameterName(String smsMobileParameterName) {
        this.smsMobileParameterName = smsMobileParameterName;
    }

    public String getImageWidthParameterName() {
        return imageWidthParameterName;
    }

    public void setImageWidthParameterName(String imageWidthParameterName) {
        this.imageWidthParameterName = imageWidthParameterName;
    }

    public String getImageHeightParameterName() {
        return imageHeightParameterName;
    }

    public void setImageHeightParameterName(String imageHeightParameterName) {
        this.imageHeightParameterName = imageHeightParameterName;
    }

    public String getImageLengthParameterName() {
        return imageLengthParameterName;
    }

    public void setImageLengthParameterName(String imageLengthParameterName) {
        this.imageLengthParameterName = imageLengthParameterName;
    }

    public String getImageExpireInParameterName() {
        return imageExpireInParameterName;
    }

    public void setImageExpireInParameterName(String imageExpireInParameterName) {
        this.imageExpireInParameterName = imageExpireInParameterName;
    }

    public String getImageTypeParameterName() {
        return imageTypeParameterName;
    }

    public void setImageTypeParameterName(String imageTypeParameterName) {
        this.imageTypeParameterName = imageTypeParameterName;
    }
}
