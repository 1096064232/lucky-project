package com.lucky.core.pay.payment.weixin;

import com.github.wxpay.sdk.WXPayConfig;
import com.lucky.core.property.LuckyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *  微信支付配置
 */
public class WeixinPaymentConfig implements WXPayConfig {

    private LuckyProperties luckyProperties;

    /**
     *  证书输入流
     */
    private byte[] certData = null;

    public WeixinPaymentConfig(LuckyProperties luckyProperties) throws Exception {
        String certPath = luckyProperties.getPay().getWeixin().getCertPath();
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
        this.luckyProperties=luckyProperties;

    }

    @Override
    public String getAppID() {
        return luckyProperties.getPay().getWeixin().getAppid();
    }

    @Override
    public String getMchID() {
        return luckyProperties.getPay().getWeixin().getMchId();
    }

    @Override
    public String getKey() {
        return luckyProperties.getPay().getWeixin().getKey();
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return luckyProperties.getPay().getWeixin().getHttpConnectTimeoutMs();
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return luckyProperties.getPay().getWeixin().getHttpReadTimeoutMs();
    }
}
