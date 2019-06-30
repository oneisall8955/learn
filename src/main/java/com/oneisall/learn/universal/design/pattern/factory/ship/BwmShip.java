package com.oneisall.learn.universal.design.pattern.factory.ship;

/**
 * 宝马轮船
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:39
 */
public class BwmShip implements Ship {

    @Override
    public void drive() {
        System.out.println("宝马轮船嘟嘟嘟");
    }
}
