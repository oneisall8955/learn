package com.oneisall.learn.java.advanced.thread.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池学习
 *
 * @author : oneisall
 * @version : v1 2019/6/6 14:45
 */
@SuppressWarnings("all")
public class MainTest {
    public static void main(String[] args) {
        //无限制,可缓存线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        //创建固定大小的线程池。
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        //创建单个线程的线程池。
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        //以上线程池最终均适用以下构造方法
        //ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue)
        //最终适用线程池方式:
        //pool.execute(new CustomJob());
        newSingleThreadExecutor.execute(() -> System.out.println("aaa"));
        //线程池状态
        //RUNNING 自然是运行状态，指可以接受任务执行队列里的任务
        //SHUTDOWN 指调用了 shutdown() 方法，不再接受新任务了，但是队列里的任务得执行完毕。
        //STOP 指调用了 shutdownNow() 方法，不再接受新任务，同时抛弃阻塞队列里的所有任务并中断所有正在执行任务。
        //TIDYING 所有任务都执行完毕，在调用 shutdown()/shutdownNow() 中都会尝试更新为这个状态。
        //TERMINATED 终止状态，当执行 terminated() 后会更新为这个状态。

        //       |--->shutdown()-->SHUTDOWN-->队列里的任务得执行完毕 -------------------------
        //RUNNING                                                                            |-->TIDYING--->terminated()----> TERMINATED
        //       |--->shutdownNow()-->STOP-->抛弃阻塞队列里的所有任务并中断所有正在执行任务------

        //execute()逻辑
        /**

         //1 获取当前线程池的状态。
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            //2 当前线程数量小于 coreSize 时创建一个新的线程运行。
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);
        */
    }
}
