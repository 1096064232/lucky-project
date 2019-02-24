package com.lucky.core.property.pay.ali;

public class AliPayProperties {

    /**
     *  appid
     */
    private String appid;

    /**
     *  支付宝网关
     */
    private String wayUrl;

    /**
     *  支付宝公钥
     */
    private String alipayPublicKey;

    /**
     *  商户私钥
     */
    private String merchantPrivateKey;

    /**
     *  字符集设置
     */
    private String charset = "UTF-8";

    /**
     *  返回格式
     */
    private String format = "JSON";

    /**
     *  签名类型
     */
    private String signType = "RSA2";

    /**
     * 销售产品码  电脑网站支付为:FAST_INSTANT_TRADE_PAY  手机网站支付为 QUICK_WAP_WAY
     */
    private String productCode ="QUICK_WAP_WAY";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getWayUrl() {
        return wayUrl;
    }

    public void setWayUrl(String wayUrl) {
        this.wayUrl = wayUrl;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }


}
