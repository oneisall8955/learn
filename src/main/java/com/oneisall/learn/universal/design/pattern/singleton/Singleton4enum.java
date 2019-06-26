package com.oneisall.learn.universal.design.pattern.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 枚举单例
 * effective java:最佳的单例实现模式就是枚举模式
 * <p>
 * 利用枚举的特性，让JVM来保证线程安全和单一实例的问题。
 * <p>
 * 除此之外，写法还特别简单
 *
 * @author : oneisall
 * @version : v1 2019/6/26 10:50
 */
@SuppressWarnings("all")
public enum Singleton4enum implements Serializable {

    INSTANCE;

    public void doSomeThing() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Singleton4enum.INSTANCE.doSomeThing();
        Singleton4enum test;
        //反射 newInstance不支持,抛异常
        //->if ((clazz.getModifiers() & Modifier.ENUM) != 0)
        //            throw new IllegalArgumentException("Cannot reflectively create enum objects");
        try {
            Class<Singleton4enum> clazz = Singleton4enum.class;
            Constructor<Singleton4enum> constructor = clazz.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            test = constructor.newInstance("test", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 反序列化
        // 针对枚举,反序列化中:
        // ->ObjectInputStream.readObject();
        // ->readObject0()
        // ->case TC_ENUM: return checkResolve(readEnum(unshared));
        // ->readEnum()
        // ->Enum<?> en = Enum.valueOf((Class)cl, name);
        // ->result = en;
        // ->return result
        // 故反序列化后,实例是同一个
        byte[] serialize = SerializationUtils.serialize(Singleton4enum.INSTANCE);
        test = SerializationUtils.deserialize(serialize);
        System.out.println(test.getClass().getName() + "\n" + test.getClass().getName());
        // result->true
        System.out.println(test.getClass() == test.getClass());
        // result->true
        System.out.println(test == Singleton4enum.INSTANCE);
    }

}
