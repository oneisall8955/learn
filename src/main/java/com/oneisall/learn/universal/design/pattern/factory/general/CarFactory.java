package com.oneisall.learn.universal.design.pattern.factory.general;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;

/**
 * 工厂模式
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:58
 */
public interface CarFactory {

    /**
     * 创建车
     *
     * @return 返回具体的车
     */
    Car createCar();
}
