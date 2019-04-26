package com.oneisall.learn.advanced.concurrent.chapter01;

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
public class Test {

    private long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws Exception {
        final Test test = new Test();
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
        long calc = Test.calc();
        System.out.println(calc);
    }
}
