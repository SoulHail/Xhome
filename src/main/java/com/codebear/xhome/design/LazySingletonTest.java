package com.codebear.xhome.design;

/**
 * 设计模式篇：
 * 单例模式-懒汉篇
 *
 * 1、线程安全问题
 * 2、double check 加锁优化
 * 3、编译器（JIT），CPU有可能对指令进行重排，导致使用到尚未初始化的实例，可以通过添加volatile关键字进行修饰
 */
public class LazySingletonTest {
    public static void main(String[] args) {

        /**
         * 单线程情况下，生成两个实例看是否一致
         */
//        LazySingleton instance = LazySingleton.getInstance();
//        LazySingleton instance1 = LazySingleton.getInstance();
//        System.out.println(instance == instance1);

        /**
         * 多线程情况下
         */
        new Thread( ()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

        new Thread( ()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }
}

class LazySingleton {

    private volatile static LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                    // 字节码层
                    // JIT、CPU
                    // 1、分配空间
                    // 2、初始化
                    // 3、应用赋值
                    // 加入volatile防止指令重排
                }
            }
            instance = new LazySingleton();
        }
        return instance;
    }
}