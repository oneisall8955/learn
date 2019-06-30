package com.oneisall.learn.universal.design.pattern.factory.general;

import com.oneisall.learn.universal.design.pattern.factory.car.BwmCar;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;

/**
 * 宝马车工厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 15:01
 */
public class BwmCarFactory implements CarFactory {


    private BwmCarFactory() {

    }

    private static CarFactory instance = new BwmCarFactory();

    public static CarFactory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new BwmCar();
    }

}
