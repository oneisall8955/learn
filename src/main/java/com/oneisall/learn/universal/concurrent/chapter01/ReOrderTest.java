package com.oneisall.learn.universal.concurrent.chapter01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 重排序测试
 *
 * @author : oneisall
 * @version : v1 2020/5/19 18:31
 */
public class ReOrderTest {
    private static int x = 0, y = 0;
    // 有序性 内存屏障 X86在volatile 写之前添加一个StoreLoad
    // lock;addl $0,0(%%rsp) ,总线锁
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        int i = 0;
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        while (true) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread thread1 = new Thread(() -> {
                shortWait(20000);
                a = 1;//1 volatile写 StoreLoad
                // 手动添加内存屏障
                // Unsafe.getUnsafe().storeFence();
                try {
                    ((Unsafe) f.get(null)).storeFence();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                x = b;//2
            });
            Thread thread2 = new Thread(() -> {
                b = 1;//3
                // Unsafe.getUnsafe().storeFence();
                try {
                    ((Unsafe) f.get(null)).storeFence();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                y = a;//4
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            // 非重排序
            // 1，2，3，4 x0,y1
            // 3，4，1，2 x1,y0
            // 1，3，4，2 x1,y1
            // 3，1，2，4 x1,y1
            // 重排序后
            // 2，3，4，1 x0,y0

            System.out.println("第" + i + "次: x:" + x + ",y:" + y);
            if (x == 0 && y == 0) {
                break;
            }
        }
    }

    private static void shortWait(int interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start - interval >= end);
    }

}
