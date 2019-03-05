package com.lucky.core.property.security.verification;

/**
 *  阿里云短信服务配置项
 * @Author ouyangfan
 * @Date 2019/3/220:36
 **/
public class AliSmsCodeProperties {

    /**
     *  产品码,开发者无需替换
     */
    private String productCode = "Dysmsapi";

    /**
     *  产品域名,开发者无需替换
     */
    private String productDomain = "dysmsapi.aliyuncs.com";

    private String regionId = "cn-hangzhou";

    private String accessKeyId;

    private String accessSecret;

    /**
     * 短信签名
     */
    private String signName;

    /***
     * 短信模板
     */
    private String templateCode;

    /**
     *  短信模板的参数名
     */
    private String templateParamName;

    public String getTemplateParamName() {
        return templateParamName;
    }

    public void setTemplateParamName(String templateParamName) {
        this.templateParamName = templateParamName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(String productDomain) {
        this.productDomain = productDomain;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
