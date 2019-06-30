package com.oneisall.learn.universal.design.pattern.factory.abstraction;

import com.oneisall.learn.universal.design.pattern.factory.car.BwmCar;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.BwmShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 宝马制造厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:53
 */
public class BwmFactory implements Factory {

    private BwmFactory() {

    }

    private static Factory instance = new BwmFactory();

    public static Factory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new BwmCar();
    }

    @Override
    public Ship createShip() {
        return new BwmShip();
    }
}
