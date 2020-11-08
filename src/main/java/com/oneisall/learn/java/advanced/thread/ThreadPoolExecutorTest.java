package com.oneisall.learn.java.advanced.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {

    private static ThreadPoolExecutor pool;

    static {
        /*
         *public ThreadPoolExecutor(
         * int corePoolSize,
         * int maximumPoolSize,
         * long keepAliveTime,
         * TimeUnit unit,
         * BlockingQueue<Runnable> workQueue,
         * RejectedExecutionHandler handler);
         *
         * */
        pool = new ThreadPoolExecutor(
                6, 12, 5L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1024),
                new MyThreadFactory(),
                new MyHandler()
        );
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("触发自定义拒绝策略，开始。。。");
            r.run();
            System.out.println("触发自定义拒绝策略，结束。。。");
        }
    }

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "myPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // executeTest();
        // submitTest();
    }

    static void executeTest() throws InterruptedException {
        int i = 0;
        do {
            int finalI = i;
            pool.execute(() -> {
                System.out.println("正在执行第" + finalI + "个任务");
                if (finalI % 3 == 0) {
                    throw new RuntimeException("执行第" + finalI + "个任务的时候发生了异常");
                }
            });
            TimeUnit.SECONDS.sleep(2L);
            i++;
        } while (i != 20);
        pool.shutdown();
    }

    static void submitTest() throws InterruptedException {
        Callable<Integer> task = () -> 1;
        Future<Integer> submit = pool.submit(task);
        try {
            submit.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
