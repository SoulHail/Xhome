package com.codebear.xhome.test;

public class Bbb extends Thread {

    public void run() {
        System.out.println("执行run方法11111");
    }
    public static void main(String[] args) {
        new Bbb().start();
    }
}
