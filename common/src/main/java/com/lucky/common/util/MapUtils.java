package com.lucky.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    private static  Logger logger = LoggerFactory.getLogger(MapUtils.class);

    public static Map<String, Object> conversion(Map<String, String> map) {
        Map<String, Object> result = new HashMap<>();
        if (map == null)
            return result;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    /**
     *  打印Map集合
     * @param map
     */
    public static void printMap(Map map){
        if(map == null)
            logger.info("集合为空");
       for(Object key:map.keySet()){
           logger.info("key is :{},value is :{}, value is :",key,(String)map.get(key));
       }
    }
}
