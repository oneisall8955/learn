package com.oneisall.learn.java.basic.special.grammar;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2019/5/20 17:07
 */
public class ContinueTest {
    public static void main(String[] args) {
        List<Integer> integerList = Lists.newArrayList(1, 2, 3);
        List<String> stringList = Lists.newArrayList("A", "B", "C");
        outer:
        for (Integer integer : integerList) {
            System.out.println(integer);
            for (String string : stringList) {
                System.out.println(string);
                if("B".equals(string)){
                    break outer;
                }
            }
        }
    }
}
