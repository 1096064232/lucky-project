package com.lucky.common.util;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtils {

    /**
     * 不可实例化
     */
    private UserAgentUtils() {
    }

    /**
     *  获取当前用户客户端
     * @param request
     * @return
     */
    public static Device getCurrentDevice(HttpServletRequest request){
       return  DeviceUtils.getCurrentDevice(request);
    }
}
