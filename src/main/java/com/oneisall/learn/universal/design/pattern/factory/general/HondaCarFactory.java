package com.oneisall.learn.universal.design.pattern.factory.general;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.car.HondaCar;

/**
 * 本田车工厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 15:01
 */
public class HondaCarFactory implements CarFactory {


    private HondaCarFactory() {

    }

    private static CarFactory instance = new HondaCarFactory();

    public static CarFactory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new HondaCar();
    }

}
