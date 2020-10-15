package com.codebear.xhome.design;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 设计模式篇：
 * 单例模式-静态内部类实现
 * <p>
 * 1、本质上是利用类的加载机制来保证线程安全
 * 2、只有在实际使用的时候，才会触发类的初始化，所以也是懒加载的一种形式
 */
public class InnerClassSingletonTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
//        InnerClassSingleton instance = InnerClassSingleton.getInstance();
//        InnerClassSingleton instance1 = InnerClassSingleton.getInstance();
//        System.out.println(instance == instance1);

//        new Thread( ()->{
//            InnerClassSingleton instance = InnerClassSingleton.getInstance();
//            System.out.println(instance);
//        }).start();
//
//        new Thread( ()->{
//            InnerClassSingleton instance = InnerClassSingleton.getInstance();
//            System.out.println(instance);
//        }).start();

        Constructor<InnerClassSingleton> declaredConstructor = InnerClassSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();

        InnerClassSingleton instance = InnerClassSingleton.getInstance();
        System.out.println(instance == innerClassSingleton);
    }
}

class InnerClassSingleton {
    private static class InnerClassHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {

        if (InnerClassHolder.instance != null) {
            throw new RuntimeException("单例不允许多个实例！");
        }

    }

    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.instance;
    }

}
