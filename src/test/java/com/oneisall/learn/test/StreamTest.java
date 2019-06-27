package com.oneisall.learn.test;

import com.google.common.collect.Lists;
import com.oneisall.learn.java.utils.CollectionUtil;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * 流测试
 *
 * @author : oneisall
 * @version : v1 2019/6/27 09:01
 */
public class StreamTest {
    public static void main(String[] args) {
        BinaryOperator<Integer> accumulator = (i1, i2) -> i1 + i2;
        Integer all = Stream.of(1, 2, 3).reduce(accumulator).orElse(0);
        System.out.println(all);
        System.out.println(CollectionUtil.reduce(Lists.newArrayList(1, 2, 3), accumulator).orElse(0));
    }
}
