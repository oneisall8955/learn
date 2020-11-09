package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/28 10:27
 */
public class FutureTaskTest {

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            return 333;
        }
    }

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask, "t1").start();
        new Thread(futureTask, "t2").start(); // call 方法只会执行一次！
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
