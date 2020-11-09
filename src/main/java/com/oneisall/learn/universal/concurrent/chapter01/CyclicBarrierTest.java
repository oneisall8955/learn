package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/24 20:02
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤了神龙！");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "\t 收集了一颗龙珠 awaiting");
                    try {
                        cyclicBarrier.await(5L, TimeUnit.SECONDS);
                        System.out.println(name + "\t await ok");
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t" + (i+1)).start();
        }

    }
}
