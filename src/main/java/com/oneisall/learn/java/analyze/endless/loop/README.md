## Java死循环定位
1. top 命令,查出CPU使用率最高的Java进程
   > \# top ##键入大写P,按照CPU占用率排序(大写M内存排序) 
2. 根据以上top命令,查的该java进程的的PID 如10086
(如需确认这个PID是否是Java进程,可用cat /proc/10086/cmdline查看执行命令或者jps查看是否存在这个PID)
3. top 命令,查出如上进程中CPU使用率最高的线程号
   > \# top -Hp 10086 # -H 线程模式, -p 指定pid
4. 根据以上top命令,查的该线程号 如10087
5. jstack 命令,dump出线程快照
   > \# jstack 10086 > jstack.dump
6. 将线程号转换成16进制,(可用window10计算机/在线转换进制/Linux打印命令)
   > \# printf '%x\n' 10087 <br>
   > \# 2767 #这个就是线程号的16进制值,用于在dump文件定位查看线程
7. vim查看dump文件,在vim内查找'0x2767'
8. 定位问题代码,如下main线程，处于runnable状态，在main方法中,Java文件的第11行，也就是我们死循环的位置：
    ```$java
      "main" #1 prio=5 os_prio=0 tid=0x00007ff97c00b000 nid=0x2767 runnable [0x00007ff98361f000]
         java.lang.Thread.State: RUNNABLE
          at com.oneisall.learn.java.analyze.endless.loop.EndlessLoopTest.main(EndlessLoopTest.java:11)
    ```
9. 总结:
    1. 找到进程号
    2. 找到线程号
    3. 找到问题原因，代码行数