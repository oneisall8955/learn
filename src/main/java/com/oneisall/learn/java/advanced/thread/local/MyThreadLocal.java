package com.oneisall.learn.java.advanced.thread.local;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 模拟ThreadLocal,ThreadLocal基本原理
 * <p>
 * ThreadLocal类中有一个静态内部类ThreadLocalMap(概念上类似于Map)，
 * 用键值对的形式存储每一个线程的变量副本，
 * ThreadLocalMap中元素的key为当前ThreadLocal对象，
 * 而value对应线程的变量副本
 * 我们使用Map来代替ThreadLocalMap，创建一个简易的类ThreadLocal实现
 *
 * @author : oneisall
 * @version : v1 2019/7/15 13:48
 */
@SuppressWarnings("all")
public class MyThreadLocal<T> {

    /**
     * 早期JDK版本ThreadLocal是持有一个MAP,但后面更换了设计思路
     * 实际上并不是ThreadLocal持有一个Map,而是ThreadLocal类中有一个静态内部类ThreadLocalMap,key为ThreadLocal自身的引用
     * 每一个线程均持有ThreadLocal.ThreadLocalMap的实例,set/get/remove都是当前线程中获取ThreadLocalMap的实例,进行操作
     */
    private final Map<Thread, T> threadLocalMap = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            threadLocalMap.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T t = threadLocalMap.get(key);
            if (t == null) {
                return initialValue();
            } else {
                return t;
            }
        }
    }

    public T initialValue() {
        return null;
    }

    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new MySuppliedThreadLocal<>(supplier);
    }

    static final class MySuppliedThreadLocal<T> extends ThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        MySuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }
}
