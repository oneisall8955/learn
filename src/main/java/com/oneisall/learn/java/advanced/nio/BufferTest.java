package com.oneisall.learn.java.advanced.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存储。缓冲区就是数组。用于存储不同数据类型的 数据。
 * <p>
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * <p>
 * - ByteBuffer
 * - CharBuffer
 * - ShortBuffer
 * - IntBuffer
 * - LongBuffer
 * - FloatBuffer
 * - DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 * <p>
 * 二、缓冲区存储数据的两个核心方法
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 * <p>
 * 三、缓冲区中的四个核心属性
 * capacity : 容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作的数据的大小（位置的值）。（limit 及 limit 后数据不能读写）
 * position : 位置，表示缓冲区中正在操作（读/写）数据的位置。
 * mark : 标记位置，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 * <p>
 * mark <= position <= limit <= capacity
 *
 * @author : oneisall
 * @version : v1 2020/2/25
 */
public class BufferTest {
  @Test
  public void test1() {
    // 1. 分配一个指定大小的缓冲区
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    System.out.println("---------allocate---------");
    // 1024，容量就是初始化申请的大小
    System.out.println("capacity :" + buffer.capacity());
    // 1024，最多可以读写到申请大小1024这个位置
    System.out.println("limit    :" + buffer.limit());
    // 0，当前操作的位置默认从0开始
    System.out.println("position :" + buffer.position());

    String str = "abcde";

    buffer.put(str.getBytes());

    // 2. 使用 put() 存入数据到缓冲区中
    System.out.println("---------put---------");
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 1024， 当前为写模式，可以继续写到1024这个位置
    System.out.println("limit    :" + buffer.limit());
    // 5，当前已经写入了5个字节，位置从0到了5
    System.out.println("position :" + buffer.position());

    // 3. 使用 flip() 切换到读取数据模式
    buffer.flip();
    System.out.println("---------flip---------");
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 5，切换到读模式后，limit等于上一个模式下的position的值
    System.out.println("limit    :" + buffer.limit());
    // 0，切换模式后，位置回到0
    System.out.println("position :" + buffer.position());

    // 4. 使用 get() 读取缓冲区中的数据
    System.out.println("---------get---------");
    byte[] dst = new byte[buffer.limit()];
    // 从 dst 数组的第 0 角标开始,读取 limit() 长度的数据，即长度为 5 的数据
    buffer.get(dst, 0, buffer.limit());
    System.out.println("dst      :" + new String(dst));
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 5，读取模式，limit 不变
    System.out.println("limit    :" + buffer.limit());
    // 5，从0开始读取了长度为 5 的数据，位置到 5 了
    System.out.println("position :" + buffer.position());

    // 5. 使用 rewind() ，重置操作位置，可重复操作
    System.out.println("---------rewind---------");
    buffer.rewind();
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 5，rewind 仅仅操作当前位置，对 limit 没有影响
    System.out.println("limit    :" + buffer.limit());
    // 0，rewind 重置了当前操作位置到 0
    System.out.println("position :" + buffer.position());

    // 6. 使用 clear() ，清空缓存，但是缓冲区中的数据依然存在，此时的缓冲区中的数据为 “被遗忘” 状态。
    // 意思是，不能通过 limit，position 来确认可操作的数据长度
    System.out.println("---------clear---------");
    buffer.clear();
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 1024，清空后限制恢复到初始的容量大小
    System.out.println("limit    :" + buffer.limit());
    // 0，清空后，重置了当前操作位置到 0
    System.out.println("position :" + buffer.position());

    // 7. 测试缓冲区中被遗忘仍然可以获取数据，但是会导致
    System.out.println("---------after clear and get---------");
    System.out.println("get af cl:" + (char) buffer.get());
    // 1024，容量就是初始化申请的大小，不变
    System.out.println("capacity :" + buffer.capacity());
    // 1024，读取模式，limit 不变
    System.out.println("limit    :" + buffer.limit());
    // 1，读取了一个数据，操作移动到一个位置，故位置为 1
    System.out.println("position :" + buffer.position());
  }

  @Test
  public void testRemark() {
    String str = "abcde";
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    buffer.put(str.getBytes());
    buffer.flip();
    byte[] dst = new byte[buffer.limit()];
    // 从 dst 数组的第 0 角标开始,读取长度 2 的数据，即 ab
    buffer.get(dst, 0, 2);
    // ab
    System.out.println(new String(dst, 0, 2));
    // position = 2
    System.out.println(buffer.position());
    // remark 标记
    buffer.mark();
    // 从 dst 数组的第 2 角标开始,读取长度 3 的数据，即 cde
    buffer.get(dst, 2, 3);
    // position = 5
    System.out.println(buffer.position());
    // cde
    System.out.println(new String(dst, 2, 3));
    // 回到了 remark 标记的位置
    buffer.reset();
    // 故为：2
    System.out.println(buffer.position());

    // hasRemaining 是否还剩余可以操作的位置 true
    System.out.println(buffer.hasRemaining());
    // remaining 剩余操作的数据数量 3
    System.out.println(buffer.remaining());
  }

}