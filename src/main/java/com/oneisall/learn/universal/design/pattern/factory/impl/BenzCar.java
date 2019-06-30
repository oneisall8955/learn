package com.oneisall.learn.universal.design.pattern.factory.impl;

import com.oneisall.learn.universal.design.pattern.factory.Car;

/**
 * 奔驰车
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:19
 */
public class BenzCar implements Car {

    @Override
    public void run() {
        System.out.println("我是奔驰车,跑起来超级快");
    }
}
