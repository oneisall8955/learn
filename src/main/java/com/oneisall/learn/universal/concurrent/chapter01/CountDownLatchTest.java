package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/24 20:02
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MICROSECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t 同学离开了教室");
                countDownLatch.countDown();
            }, "t" + i).start();
        }
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println("##########" + Thread.currentThread().getName() + " await before");
                    countDownLatch.await();
                    System.out.println("##########" + Thread.currentThread().getName() + " await after");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "tt" + i).start();
        }
        System.out.println("##########班长关灯 before");
        countDownLatch.await();
        System.out.println("##########班长关灯，锁门离开教室");
    }
}
