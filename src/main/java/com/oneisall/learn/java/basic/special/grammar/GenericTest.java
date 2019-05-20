package com.oneisall.learn.java.basic.special.grammar;

import com.oneisall.learn.java.advanced.genericity.Generic;

/**
 * TODO :please describe it in one sentence
 *
 * @author : oneisall
 * @version : v1 2019/5/20 18:30
 */
public class GenericTest {
    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        Generic<String>[] array = (Generic<String>[]) new Generic[2];

        Generic<String> aaa = new Generic<>("AAA");
        Generic<String> bbb = new Generic<>("BBB");
        array[0] = aaa;
        array[1] = bbb;

        for (Generic<String> stringGeneric : array) {
            System.out.println(stringGeneric.getKey());
        }
        /*ERROR
        Generic<Integer> ccc = new Generic<>(1);
        array[2] = ccc;*/
    }
}
