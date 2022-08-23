package com.oneisall.learn.helper;

/**
 * @author liuzhicong
 **/
public interface ConQuestion {

    String questionKey();

    default String questionValue() {
        return questionKey();
    }
}
