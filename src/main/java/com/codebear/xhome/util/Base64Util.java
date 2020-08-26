package com.codebear.xhome.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Random;

/**
 * Base64加密工具类
 * 该加密方式容易被破解，相同字符串加密得到的内容相同，可被反推出原文，且密文的长度与原文的长度成正比
 */
public class Base64Util {

    private static final String[] saltArr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static String getSalt() {
        int length = saltArr.length;
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(saltArr[random.nextInt(length)]);
            salt.append(rand);
        }
        System.out.println(salt);
        return salt.toString();
    }

    /**
     * Base64加密，加盐
     *
     * @param str
     * @return
     */
    private static String enCode(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String salt = getSalt();
        str += salt;
        byte[] b = Base64.encodeBase64(str.getBytes(), true);
        return new String(b);
    }

    /**
     * Base64解密，加盐
     *
     * @param str
     * @return
     */
    private static String deCode(String str, String salt) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(salt)) {
            return null;
        }
        String plainText = new String(Base64.decodeBase64(str.getBytes()));
        if (StringUtils.isBlank(plainText)) {
            return null;
        }
        int length = plainText.length();
        return plainText.substring(0, length - 6);
    }

    /**
     * Base64加密
     *
     * @param str
     * @return
     */
    private static String getEncode(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        byte[] b = Base64.encodeBase64(str.getBytes(), true);
        return new String(b);
    }

    /**
     * Base64解密
     *
     * @param str
     * @return
     */
    private static String getDecode(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        byte[] b = Base64.decodeBase64(str.getBytes());
        return new String(b);
    }

    /*public static void main(String[] args) {
        String code = "测试";
        String codeEnc = getEncode(code);
        String codeEncSalt = enCode(code);
        System.out.println("base64直接加密：" + codeEnc);
        System.out.println("base64加盐加密：" + codeEncSalt);

        String original = getDecode("5rWL6K+V");
        String originalSalt = deCode("5rWL6K+VZ3AzcnVm","gp3ruf");
        System.out.println("base64直接解密：" + original);
        System.out.println("base64加盐解密：" + originalSalt);
    }
*/
}
