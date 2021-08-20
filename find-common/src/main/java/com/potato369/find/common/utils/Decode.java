package com.potato369.find.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class Decode {
    /**
     * 生成秘钥
     *
     * @param
     * @return
     */
    public static String generateDecode() throws UnsupportedEncodingException {
        KeyGenerator keyGen = null;//密钥生成器
        try {
            keyGen = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.init(56);//初始化密钥生成器
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        byte[] key = secretKey.getEncoded();//密钥字节数组
        // 进行base64编码
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }

    /**
     * 进行加密
     *
     * @param string
     * @param key
     * @return
     */
    public static String encryptionDecode(String string, String key) {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "DES");//恢复密钥
        Cipher cipher = null;//Cipher完成加密或解密工作类
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//对Cipher初始化，加密模式
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] cipherByte = null;
        try {
            cipherByte = cipher.doFinal(string.getBytes());//加密data
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(cipherByte);
    }

    /**
     * 进行解密
     *
     * @param string
     * @param key
     * @return
     */
    public static String decryptDecode(String string, String key) {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "DES");//恢复密钥
        Cipher cipher = null;//Cipher完成加密或解密工作类
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);//对Cipher初始化，解密模式
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] cipherByte = new byte[0];//解密data
        try {
            cipherByte = cipher.doFinal(Base64.getDecoder().decode(string));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(cipherByte);
    }
}