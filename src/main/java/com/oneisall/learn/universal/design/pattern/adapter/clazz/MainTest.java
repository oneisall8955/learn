package com.oneisall.learn.universal.design.pattern.adapter.clazz;

/**
 * 适配器测试
 *
 * @author : oneisall
 * @version : v1 2019/7/2 09:56
 */
public class MainTest {
    public static void main(String[] args) {
        TurkeyAdapter fakeDuck = new TurkeyAdapter();
        fakeDuck.quack();
        fakeDuck.fly();
    }
}
