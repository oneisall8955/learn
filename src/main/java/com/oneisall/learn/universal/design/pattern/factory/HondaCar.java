package com.oneisall.learn.universal.design.pattern.factory;

import com.oneisall.learn.universal.design.pattern.factory.Car;

/**
 * 本田车
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:21
 */
public class HondaCar implements Car {

    @Override
    public void run() {
        System.out.println("我是本田车,跑起来快");
    }
}
