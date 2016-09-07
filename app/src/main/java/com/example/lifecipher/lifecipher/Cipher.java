package com.example.lifecipher.lifecipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhaiyi on 16/9/5.
 */
public class Cipher {
    /**
     * 加密
     *
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] cipher(String key) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] keyBytes = key.getBytes();
        return md5.digest(keyBytes);
    }

    /**
     * 转成string
     *
     * @param enBytes
     * @return
     */
    public static String toString(byte[] enBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < enBytes.length; i++) {
            //将byte转换成可读的char 见ascii码表
            int x = (int) ((126 - 32) * 1.0 / 255 * enBytes[i] + (128 * 126 + 127 * 32) * 1.0 / 255);
            stringBuffer.append((char) x);
        }
        return stringBuffer.toString();
    }

}
