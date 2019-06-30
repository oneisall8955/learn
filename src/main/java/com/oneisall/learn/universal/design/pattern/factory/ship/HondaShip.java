package com.oneisall.learn.universal.design.pattern.factory.ship;

/**
 * 本田轮船
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:39
 */
public class HondaShip implements Ship {

    @Override
    public void drive() {
        System.out.println("本田轮船嘟嘟嘟");
    }
}
