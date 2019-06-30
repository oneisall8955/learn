package com.oneisall.learn.universal.design.pattern.factory.ship;

/**
 * 奔驰轮船
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:39
 */
public class BenzShip implements Ship {

    @Override
    public void drive() {
        System.out.println("奔驰轮船嘟嘟嘟");
    }
}
