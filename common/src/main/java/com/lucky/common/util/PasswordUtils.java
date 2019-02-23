package com.lucky.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtils{

    private static String key = "0gTaW6M18qtQkUzT";
    private static String vec = "8504078927236707";
    public static String encrypt(String password){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(vec.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(password.getBytes("utf-8"));
            return Base64.encode(encrypted);
        } catch (Exception e){
            return null;
        }
    }

    public static String decrypt(String enPassword){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(vec.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(Base64.decode(enPassword));
            return new String(encrypted, "UTF-8");
        } catch (Exception e){
            return null;
        }
    }
}
