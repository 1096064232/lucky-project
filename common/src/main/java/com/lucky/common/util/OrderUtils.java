package com.lucky.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderUtils {

    /**
     *  订单编号组成是否符合规则 数字、下划线、字母
     * @param orderNo
     * @return
     */
    public static boolean validateOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo))
            return false;
        String regex = "^[0-9]|[a-z]|[A-Z]|_$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        char[] strChar = orderNo.toCharArray();
        for (char cha : strChar) {
            matcher = pattern.matcher(String.valueOf(cha));
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "01212长a**sddA12SDF!@#$#$#%^145化加";
        System.out.println(validateOrderNo(str));
    }


}
