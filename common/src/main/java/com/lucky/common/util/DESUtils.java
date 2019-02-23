package com.lucky.common.util;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;

public class DESUtils {

    public static String strDefaultKey = "tool654@#(*loll)(*maa&&^%$";// 字符串默认键值
    private Cipher encryptCipher = null;// 加密工具
    private Cipher decryptCipher = null;// 解密工具

    /**
     * 默认构造方法，使用默认密钥
     *
     * @throws Exception
     */
    public DESUtils() {
        this(strDefaultKey);
    }

    /**
     * DES字符串加密 指定密钥构造方法
     *
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESUtils(String strKey) {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
            Key key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密字节数组
     *
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) {
        byte[] encrypt = null;
        try {
            encrypt = encryptCipher.doFinal(arrB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }

    /**
     * 加密字符串
     *
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) {
        return Base64.encode(encrypt(strIn.getBytes()));
    }

    /**
     * 解密字节数组
     *
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) {
        byte[] decrypt = null;
        try {
            decrypt = decryptCipher.doFinal(arrB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt;
    }

    /**
     * 解密字符串
     *
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn) {
        return new String(decrypt(Base64.decode(strIn)));
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        byte[] arrB = new byte[8];// 创建一个空的8位字节数组（默认值为0）
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {// 将原始字节数组转换为8位
            arrB[i] = arrBTmp[i];
        }
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");// 生成密钥
        return key;
    }

    /**
     * main方法
     */
    public static void main(String[] args) {
        String content = "1|120.0.2.1|20160102332200";
        DESUtils des;
        try {
            des = new DESUtils();
            System.out.println("加密前的字符：" + content);
            String desContent = des.encrypt(content);
            System.out.println("加密后的字符：" + desContent);
            System.out.println("解密后的字符：" + des.decrypt(desContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
