package com.lucky.common.util;


import com.alibaba.fastjson.JSON;
import java.util.Map;

public final class JsonUtil {

    public static Map jsonToMap(String json) {
        return (Map) JSON.parse(json);
    }

}
