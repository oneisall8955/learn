package com.oneisall.learn.universal.concurrent.chapter01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并发原因:可见性问题
 * 一个线程对共享变量的修改，另外一个线程能够立刻看到，我们称为可见性
 * 多核时代，每颗 CPU 都有自己的缓存，
 * 当多个线程在不同的 CPU 上执行时，这些线程操作的是不同的 CPU 缓存。
 * 线程 A 操作的是 CPU-1 上的缓存，
 * 而线程 B 操作的是 CPU-2 上的缓存，
 * 很明显，这个时候线程 A 对变量 count 的操作对于线程 B 而言就不具备可见性了
 *
 * @author oneisall
 * @version v1 2019/4/26 11:39
 * @see <a href="https://time.geekbang.org/column/article/83682">https://time.geekbang.org/column/article/83682</a>
 */
@SuppressWarnings("all")
public class Visibility {

    private long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws Exception {
        final Visibility test = new Visibility();
        // 创建两个线程，执行 add() 操作
        Thread threadA = new Thread(() -> {
            test.add10K();
        });
        Thread threadB = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        threadA.start();
        threadB.start();
        // 等待两个线程执行结束
        threadA.join();
        threadB.join();
        return test.count;
    }

    public static void main(String[] args) throws Exception {
        /*long calc = Visibility.calc();
        System.out.println(calc);*/
        new Visibility().test();
    }

    // volatile : lock addl $0x0,(%rsp) ，触发缓存一致性协议,总线锁
    public static volatile boolean initFlag = false;

    public static Map<String, Boolean> map = new HashMap<>();
    public static List<String> list = new ArrayList<>();
    public static int[] array = new int[100];

    {
        map.put("flag", false);
    }

    public void shortWait(int time) {
        try {
            Thread.sleep(0, time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() throws InterruptedException {
        Visibility visibility = new Visibility();
        Thread threadA = new Thread(() -> {
            System.out.println("wait data");
            // while (!visibility.initFlag) { // 有问题
            // while (list.size() <= 10) { // 有问题
            // while (!map.get("flag")) { // 没问题
            while (array[10] == 0) { // 没问题
                // while (!initFlag) { // 有问题

                 /*没问题，由于放弃了CPU，下次将进行上下文切换，CPU清理了缓存
                 try {
                     Thread.sleep(10);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 */

                // shortWait(10000);没问题，CPU清理了缓存

                // shortWait(1000);有问题，休眠事件过短，清理缓存时候，上下文切换，下次获得CPU时间片时，二级缓存或三级缓存可能还存在
                System.out.println("while 循环ing"); // 没问题
            }
            System.out.println("threadA OK");
        });
        threadA.start();
        Thread.sleep(2000);

        Thread threadB = new Thread(() -> {
            System.out.println("init data ...");
            map.put("flag", true);
            initFlag = true;
            visibility.initFlag = true;
            int i = 0;
            do {
                list.add("1");
                array[i] = i;
                if (list.size() == 10) {
                    System.out.println("init data end");
                }
                i++;
            } while (list.size() < 100);
            System.out.println("threadB OK");
        });
        threadB.start();
    }
}
