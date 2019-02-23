package com.lucky.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private  String sKey="0123456789abcdef";
    private  String ivParameter="0123456789abcdef";
    public AESUtils(String key, String iv){
        this.sKey = key;
        this.ivParameter = iv;
    }
    public  String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return encodeBytes(encrypted);
    }


    public String encodeBytes(byte[] bytes){
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<bytes.length;i++){
            buffer.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            buffer.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }
        return buffer.toString();
    }
}
