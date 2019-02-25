package com.lucky.core.property.pay.weixin;

import com.github.wxpay.sdk.WXPayConstants;

public class WeixinPayProperties {

    /**
     *  appid
     */
    private String appid;

    /**
     *  商户号
     */
    private String mchId;

    /**
     *  key
     */
    private String key;

    /**
     *  HTTP连接超时时间(毫秒)
     */
    private int httpConnectTimeoutMs = 2000;

    /**
     *  HTTP读取超时时间(毫秒)
     */
    private int httpReadTimeoutMs = 2000;

    /**
     *  证书路径
     */
    private  String certPath;

    /**
     *  签名类型
     */
    private WXPayConstants.SignType signType = WXPayConstants.SignType.MD5;

    public WXPayConstants.SignType getSignType() {
        return signType;
    }

    public void setSignType(WXPayConstants.SignType signType) {
        this.signType = signType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public void setHttpConnectTimeoutMs(int httpConnectTimeoutMs) {
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
    }

    public int getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }

    public void setHttpReadTimeoutMs(int httpReadTimeoutMs) {
        this.httpReadTimeoutMs = httpReadTimeoutMs;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }
}
