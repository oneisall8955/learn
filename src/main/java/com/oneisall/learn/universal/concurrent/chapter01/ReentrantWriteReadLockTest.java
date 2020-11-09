package com.oneisall.learn.universal.concurrent.chapter01;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/24 20:02
 */
public class ReentrantWriteReadLockTest {

    static class Cache {

        Map<String, Object> map = new HashMap<>();

        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();
        private final Lock writeLock = lock.writeLock();

        public void set(String key, Object value) {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t set " + key);
                TimeUnit.MICROSECONDS.sleep(300);
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t set ok " + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }

        }

        public Object get(String key) {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t get " + key);
                try {
                    TimeUnit.MICROSECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object o = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t get ok " + key+",value "+ o);
                return o;
            } finally {
                readLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.set("i_" + finalI, finalI);
            }, "writeThread" + i).start();
        }

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.get("i_" + finalI);
            }, "readThread" + i).start();
        }
    }

}
