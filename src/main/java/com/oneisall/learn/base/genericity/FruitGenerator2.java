package com.oneisall.learn.base.genericity;

import java.util.Random;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2019/4/11 15:18
 */
public class FruitGenerator2 implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}