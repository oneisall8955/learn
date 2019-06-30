package com.oneisall.learn.universal.design.pattern.factory.car;

/**
 * 宝马车
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:18
 */
public class BwmCar implements Car {

    @Override
    public void run() {
        System.out.println("我是宝马车,跑起来很快");
    }
}
