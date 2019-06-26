package com.oneisall.learn.universal.design.pattern.singleton;

/**
 * 饿汉式单例
 * 优点:实现简单，而且安全可靠。
 * 缺点:可能在还不需要此实例的时候就已经把实例创建出来了，没起到lazy loading的效果
 * 点评:在一个系统中既然都需要使用了单例,为什么还需要达到lazy loading的效果呢?
 * 系统本身就需要使用的单例实例,就让class加载的时候创建就好了!!!
 *
 * @author : oneisall
 * @version : v1 2019/6/26 09:53
 */
@SuppressWarnings("unused")
public class Singleton4hunger extends Singleton {
    private static Singleton instance = new Singleton4hunger();

    private Singleton4hunger() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}
