package com.lucky.core.pay.payment.weixin.parameter;

public class WeixinPulicResponse{

    /**
     *  错误代码
     */
     private String errCode;

    /**
     *  错误代码描述
     */
    private String errCodeDes;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
