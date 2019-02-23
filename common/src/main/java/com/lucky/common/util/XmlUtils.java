package com.lucky.common.util;

public class XmlUtils {

    public static String getBetween(String xml, String start, String end){
        int startIndex = xml.indexOf(start) ;
        int endIndex = xml.indexOf(end);
        if (startIndex < 0 && endIndex < 0){
            return null;
        }
        return xml.substring(startIndex + start.length(), endIndex);
    }

    public static String getBetween1(String xml, String start, String end){
        if (xml == null){
            return null;
        }
        int startIndex = xml.indexOf(start) ;
        int endIndex = xml.indexOf(end,startIndex);
        if (startIndex < 0 && endIndex < 0){
            return null;
        }
        return xml.substring(startIndex + start.length(), endIndex);
    }
}
