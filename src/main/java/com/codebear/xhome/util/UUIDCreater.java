package com.codebear.xhome.util;

import java.util.UUID;

/**
 * 本类为生成UUID码的工具类
 */
public class UUIDCreater {

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
