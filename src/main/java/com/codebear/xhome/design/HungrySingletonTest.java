package com.codebear.xhome.design;

/**
 * 设计模式篇：
 * 单例模式-饿汉篇
 *
 * 1、加载二进制数据到内存中，生成对应的Class数据结构
 * 2、连接：a、验证 b、准备，给类的静态成员变量赋值 c、解析
 * 3、初始化：给类的静态变量赋值
 */
public class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance == instance1);
    }
}

class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return instance;
    }

}