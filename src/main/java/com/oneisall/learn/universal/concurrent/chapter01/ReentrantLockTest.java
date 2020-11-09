package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/24 20:02
 */
public class ReentrantLockTest {

    static class Phone implements Runnable {

        public synchronized void sendMsg() {
            System.out.println(Thread.currentThread().getName() + "\t" + "invoked sendMsg");
            sendEmail();
        }

        public synchronized void sendEmail() {
            System.out.println(Thread.currentThread().getName() + "\t" + "#####invoked sendEmail");
        }

        @Override
        public void run() {
            get();
        }

        Lock lock = new ReentrantLock();

        private void get() {
            lock.lock();
            // lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "invoked get");
                set();
            } finally {
                lock.unlock();
                // lock.unlock(); // 几次 lock 就需要几次 unlock
            }
        }

        private void set() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "#####invoked set");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Thread t1 = new Thread(phone::sendMsg, "t1");
        t1.start();
        Thread t2 = new Thread(phone::sendMsg, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println();
        System.out.println();
        System.out.println();
        new Thread(phone, "t4").start();
        new Thread(phone, "t5").start();
    }
}
