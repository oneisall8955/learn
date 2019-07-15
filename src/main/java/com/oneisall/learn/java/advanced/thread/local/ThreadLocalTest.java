package com.oneisall.learn.java.advanced.thread.local;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 学习
 * <p>
 * ThreadLocal字面上的意思是局部线程变量，
 * 每个线程通过ThreadLocal的get和set方法来访问和修改线程自己独有的变量。
 * 简单地说，ThreadLocal的作用就是为每一个线程提供了一个独立的变量副本，
 * 每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本
 *
 * @author : oneisall
 * @version : v1 2019/7/15 11:35
 */
public class ThreadLocalTest {
    /**
     * ThreadLocal是一个泛型类，在创建的时候需要指定变量的类型
     * ThreadLocal提供了set方法来设置变量的值，get方法获取变量的值，remove方法移除变量
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 给ThreadLocal设置初始值 重写initialValue方法
     */
    @SuppressWarnings("all")
    private static ThreadLocal<String> initialValue = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "initialValue";
        }
    };

    /**
     * 给ThreadLocal设置初始值 使用ThreadLocal的withInitial方法
     */
    private static ThreadLocal<String> withInitial = ThreadLocal.withInitial(() -> "withInitial");

    public static void main(String[] args) {
        threadLocal.set("oneisall");
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());

        System.out.println("----------");

        //给ThreadLocal设置初始值,重写initialValue
        System.out.println(initialValue.get());
        //remove无法移除初始值
        initialValue.remove();
        System.out.println(initialValue.get());
        initialValue.set("oneisall");
        System.out.println(initialValue.get());
        initialValue.remove();
        //set oneisall ,remove后还是initialValue
        System.out.println(initialValue.get());

        System.out.println("----------");

        //给ThreadLocal设置初始值,静态方法withInitial
        System.out.println(withInitial.get());
        //remove无法移除初始值
        withInitial.remove();
        System.out.println(withInitial.get());
        withInitial.set("oneisall");
        System.out.println(withInitial.get());
        withInitial.remove();
        //set oneisall ,remove后还是withInitial
        System.out.println(withInitial.get());
    }

    @SuppressWarnings("all")
    @Test
    public void threadLocalTest() throws InterruptedException {
        Random random = new Random(System.currentTimeMillis());
        Thread thread1 = new Thread(() -> {
            threadLocal.set("thread t1");
            try {
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            threadLocal.set("thread t2");
            try {
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());

        //console print:
        //thread1 thread t1
        //thread2 thread t2
        //main null
        //结果证明了ThreadLocal在每个线程间是相互独立的，threadLocal在thread1、thread2和main线程间都有一份独立拷贝。
    }


    private static MyThreadLocal<String> myThreadLocal = new MyThreadLocal<>();

    @SuppressWarnings("all")
    @Test
    public void myThreadLocalTest() throws InterruptedException {
        Random random = new Random(System.currentTimeMillis());
        Thread thread1 = new Thread(() -> {
            myThreadLocal.set("thread t1");
            try {
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + myThreadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            myThreadLocal.set("thread t2");
            try {
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + myThreadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + " " + myThreadLocal.get());

        //console print:
        //thread1 thread t1
        //thread2 thread t2
        //main null
    }

    private static InheritableThreadLocal<String> threadParam = new InheritableThreadLocal<>();

    @SuppressWarnings("all")
    @Test
    public void threadLocalErrorTest() throws InterruptedException {
        //固定池内只有存活3个线程
        ExecutorService execService = Executors.newFixedThreadPool(3);
        //死循环几次才能看出效果
        while (true) {
            Runnable t1 = () -> {
                threadParam.set("abc");
                System.out.println("t1:" + threadParam.get());
                //如果不调用remove,将引发问题
                //一个线程在线程池中同一时刻只能被调用一个
                //threadParam.remove();
            };
            execService.execute(t1);

            TimeUnit.SECONDS.sleep(1);

            Runnable t2 = () -> {
                String get = threadParam.get();
                if ("abc".equals(get)) {
                    System.out.println("->" + "t2:" + get);
                } else {
                    System.out.println("t2:" + get);
                }
            };
            execService.execute(t2);
        }
    }
}
