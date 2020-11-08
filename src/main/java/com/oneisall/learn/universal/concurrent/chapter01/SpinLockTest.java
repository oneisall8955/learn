package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁测试
 *
 * @author : oneisall
 * @version : v1 2020/5/25 13:37
 */
public class SpinLockTest {

    static class CustomLock {
        AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public void lock() {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "\t come in,try lock");
            while (!atomicReference.compareAndSet(null, thread)) {

            }
        }

        public void unlock() {
            Thread expect = Thread.currentThread();
            atomicReference.compareAndSet(expect, null);
            System.out.println(expect.getName() + "\t invoked unlock");
        }
    }

    public static void main(String[] args) {
        CustomLock lock = new CustomLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1 running");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t2 running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t2").start();
    }
}
