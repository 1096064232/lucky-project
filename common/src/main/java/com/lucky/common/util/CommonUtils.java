package com.lucky.common.util;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CommonUtils {

    private CommonUtils() {
    }

    /**
     *  打印对象
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return ReflectionToStringBuilder.toString(object, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     *  打印对象
     * @param object
     * @param toStringStyle
     * @return
     */
    public static String toString(Object object, ToStringStyle toStringStyle) {
        return ReflectionToStringBuilder.toString(object, toStringStyle);
    }
}
