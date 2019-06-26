package com.oneisall.learn.universal.design.pattern.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Constructor;

/**
 * 静态内部类单例
 * <p>
 * 静态内部类的方式实现单例模式是线程安全的，
 * <p>
 * 同时静态内部类不会在Singleton类加载时就加载，
 * <p>
 * 而是在调用getInstance()方法时才进行加载，达到了懒加载的效果
 *
 * @author : oneisall
 * @version : v1 2019/6/26 09:58
 */
@SuppressWarnings("all")
public class Singleton4innerClass extends Singleton {

    private Singleton4innerClass() {

    }

    private static class SingletonHolder {
        private static Singleton singleton = new Singleton4innerClass();
    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }

    /**
     * 反射攻击或者反序列化攻击,导致内部类单例不是最完美的方法!!!
     */
    public static void main(String[] args) throws Exception {
        Singleton instance = Singleton4innerClass.getInstance();
        Singleton newInstance;
        //反射
        Constructor<Singleton4innerClass> constructor = Singleton4innerClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        newInstance = constructor.newInstance();
        // result->false
        System.out.println(instance == newInstance);

        //反序列化
        byte[] serialize = SerializationUtils.serialize(instance);
        newInstance = SerializationUtils.deserialize(serialize);
        System.out.println(instance.getClass().getName() + "\n" + newInstance.getClass().getName());
        // result->true
        System.out.println(instance.getClass() == newInstance.getClass());
        // result->false
        System.out.println(instance == newInstance);

    }

}
