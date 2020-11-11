package com.codebear.xhome.algo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java字符串中提取数字
 */
public class GetNumFromString {
    public static void main(String[] args) {
        String a = "love12next34csde54434java";
        getAllNum(a);
        getEachNum(a);
    }

    // 这个方法的缺点：只能把数字全部提取出来，不能分别提取
    public static String getAllNum(String str){
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i ++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        System.out.println(str2);
        return str2;
    }
    // 正则表达式筛选
    public static void getEachNum(String str){
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.replaceAll("").trim());
        //System.out.println(m.replaceFirst("").trim());
    }

}
