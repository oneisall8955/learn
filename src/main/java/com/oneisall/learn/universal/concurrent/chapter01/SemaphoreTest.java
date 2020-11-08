package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/24 20:02
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println("线程" + name + "抢到车位了");
                        TimeUnit.SECONDS.sleep(finalI);
                        System.out.println("线程" + name + "停了" + finalI + "秒，离开了");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "t" + (i + 1)).start();
        }
    }
}
