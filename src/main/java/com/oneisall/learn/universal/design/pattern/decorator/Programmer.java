package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 程序员接口，会打代码
 */
public interface Programmer {

    default String skill() {
        return "小白只会BB，不会打代码";
    }

    default int salary(){
        return 0;
    }

}
