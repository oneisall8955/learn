package com.oneisall.learn.universal.concurrent.chapter01;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2020/5/23 23:51
 */
public class UnsafeCollectionTest {

    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 添加了null元素
                // 添加的长度不正确
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // AbstractCollection.toString调用iterator从而调用new Itr()最后调用checkForComodification抛出异常
                System.out.println(list);
                System.out.println(list);
            }, "t" + i).start();
        }
    }
}
