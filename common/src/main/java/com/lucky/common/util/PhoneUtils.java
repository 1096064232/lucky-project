package com.lucky.common.util;

public class PhoneUtils {

    /**
     *  手机号格式是否正确
     * @param phone
     * @return
     */
    public static boolean isTelecomPhone(String phone){
        return phone.matches("^(133|153|180|181|189|177|170|173)[0-9]{8}$");
    }
}
