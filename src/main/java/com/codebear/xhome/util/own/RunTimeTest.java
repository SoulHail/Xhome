package com.codebear.xhome.util.own;

import java.io.IOException;

/**
 * Java代码执行Linux命令
 * window用不了
 */
public class RunTimeTest {
    public static void main(String[] args) throws IOException {
        // RunTime类

        // 获取处理器个数
        int process = Runtime.getRuntime().availableProcessors();
        // 获取Jvm剩余内存
        long freeMemory = Runtime.getRuntime().freeMemory();

        System.out.println("process:" + process);
        System.out.println("freeMemory:" + freeMemory);

        Process exec = Runtime.getRuntime().exec("pwd");

        byte[] buff = new byte[1024];
        int length = exec.getInputStream().read(buff);

        // 输出命令执行结果
        System.out.println(new String(buff,0,length));
    }
}
