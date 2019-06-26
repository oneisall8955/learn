package com.oneisall.learn.universal.design.pattern.singleton;

/**
 * 线程安全的懒汉式单例
 * 使用双重检查,以及volatile关键字来修饰singleton，其最关键的作用是防止指令重排
 *
 * @author : oneisall
 * @version : v1 2019/6/26 09:58
 */
@SuppressWarnings("unused")
public class Singleton4lazySafe extends Singleton {

    /**
     * 必须要有volatile修饰防止指令重排,
     * 线程A执行到 instance=new Singleton4lazySafe(); 分解成为如下3行代码
     * <p>
     * memory = allocate(); // 1.分配对象的内存空间
     * ctorInstance(memory); // 2.初始化对象
     * instance = memory; // 3.设置instance指向刚分配的内存地址
     * <p>
     * 上述伪代码中的2和3之间可能会发生重排序，重排序后的执行顺序如下:
     * <p>
     * memory = allocate(); // 1.分配对象的内存空间
     * instance = memory; // 2.设置instance指向刚分配的内存地址，此时对象还没有被初始化
     * ctorInstance(memory); // 3.初始化对象
     * <p>
     * 当指令重排线程A执行到2时候,线程B执行到第一个判断instance==null ->false,直接返回instance,对这个对象操作则会产生错误
     */
    private static volatile Singleton instance;

    private Singleton4lazySafe() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton4lazySafe.class) {
                if (instance == null) {
                    instance = new Singleton4lazySafe();
                }
            }
        }
        return instance;
    }
}
