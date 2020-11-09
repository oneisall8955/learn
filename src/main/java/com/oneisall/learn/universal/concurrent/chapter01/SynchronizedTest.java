package com.oneisall.learn.universal.concurrent.chapter01;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/26 16:19
 */
public class SynchronizedTest {

    final Object o = new Object();

    public synchronized void methodA() {

    }

    public void methodB() {
        synchronized (o) {

        }
    }

}
