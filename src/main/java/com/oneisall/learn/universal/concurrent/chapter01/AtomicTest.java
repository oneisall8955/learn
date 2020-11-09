package com.oneisall.learn.universal.concurrent.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/22 19:30
 */
public class AtomicTest {

    @Test
    public void test00() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(0, 5);
        atomicInteger.getAndIncrement();
    }

    @Test
    public void test01() {
        User z3 = new User("z3", 20);
        User li4 = new User("li4", 25);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(z3);
        System.out.println(userAtomicReference.compareAndSet(z3, li4) + "\t" + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(z3, li4) + "\t" + userAtomicReference.get().toString());
    }


    @Test
    public void test02() {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);
        // ABA
        System.out.println(reference.compareAndSet(100, 101, 1, 2));
        System.out.println(reference.compareAndSet(101, 100, 2, 3));
        // stamp不一致，修改失败
        System.out.println(reference.compareAndSet(100, 200, 1, 2));
    }

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {
        // ABA 问题
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 200) + "\t" + atomicReference.get());
        }, "t2").start();

        // ABA问题解决
    }
}

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
