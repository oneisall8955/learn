package com.oneisall.learn.universal.design.pattern.factory.abstraction;

import com.oneisall.learn.universal.design.pattern.factory.car.BenzCar;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.BenzShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 本田制造厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:53
 */
public class BenzFactory implements Factory {

    private BenzFactory() {

    }

    private static Factory instance = new BenzFactory();

    public static Factory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new BenzCar();
    }

    @Override
    public Ship createShip() {
        return new BenzShip();
    }
}
