package com.oneisall.learn.universal.design.pattern.singleton;

/**
 * 非线程安全的懒汉式单例
 * 并发获取实例的时候，可能会存在构建了多个实例的情况
 *
 * @author : oneisall
 * @version : v1 2019/6/26 09:58
 */
@SuppressWarnings("unused")
public class Singleton4lazyUnsafe extends Singleton{
    private static Singleton instance;

    private Singleton4lazyUnsafe() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton4lazyUnsafe();
        }
        return instance;
    }
}
