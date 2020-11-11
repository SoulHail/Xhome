package com.codebear.xhome.util;

import java.security.MessageDigest;

/**
 * MD5加密，不可逆，不同数据加密得到的字符串长度一致
 */
public class MD5Util {

    /**
     * 加解密统一使用的编码方式
     */
    public static final String UTF8_ENCODING = "UTF-8";


    public static String md5Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return bytesToHex(md.digest(str.getBytes(UTF8_ENCODING)));
        } catch (Exception e) {
            new RuntimeException(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param bytes
     * @return
     * @description byte[]转16进制字符串形式
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (2 > hv.length())
                stringBuilder.append(0);
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String a = "sjskd";
        String b = "sjskddfkdsjfksdj";
        System.out.println(md5Encode(a));
        System.out.println(md5Encode(b));
    }
}
