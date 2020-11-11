package com.codebear.xhome.test;

import org.apache.ibatis.executor.Executor;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Aaa implements Runnable{
    @Override
    public void run() {
        System.out.println("执行run方法aaaa");
    }

    public static void main(String[] args) {
        //new Aaa().run();
        Aaa aaa = new Aaa();
        Thread thread = new Thread(aaa);
        thread.start();
    }
}
