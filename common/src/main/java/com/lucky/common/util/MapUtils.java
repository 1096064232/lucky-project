package com.lucky.common.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {


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
            System.err.println("集合为空");
       for(Object key:map.keySet()){
           System.err.println("key is :"+key+" value is :"+map.get(key));
       }
    }

}
