package com.oneisall.learn.universal.design.pattern.factory.general;

import com.oneisall.learn.universal.design.pattern.factory.BenzCar;
import com.oneisall.learn.universal.design.pattern.factory.Car;

/**
 * 奔驰车工厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 15:01
 */
public class BenzCarFactory implements CarFactory {

    private BenzCarFactory() {

    }

    private static CarFactory instance = new BenzCarFactory();

    public static CarFactory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new BenzCar();
    }

}
