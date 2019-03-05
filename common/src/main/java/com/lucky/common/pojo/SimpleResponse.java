package com.lucky.common.pojo;

/**
 *  通用返回
 * @Author ouyangfan
 * @Date 2019/3/221:19
 **/
public class SimpleResponse {

    private Object response;

    public SimpleResponse(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
