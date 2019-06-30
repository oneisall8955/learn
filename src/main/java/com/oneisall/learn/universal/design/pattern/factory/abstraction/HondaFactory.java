package com.oneisall.learn.universal.design.pattern.factory.abstraction;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.car.HondaCar;
import com.oneisall.learn.universal.design.pattern.factory.ship.HondaShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 奔驰制造厂
 *
 * @author : oneisall
 * @version : v1 2019/6/30 16:53
 */
public class HondaFactory implements Factory {

    private HondaFactory() {

    }

    private static Factory instance = new HondaFactory();

    public static Factory getInstance() {
        return instance;
    }

    @Override
    public Car createCar() {
        return new HondaCar();
    }

    @Override
    public Ship createShip() {
        return new HondaShip();
    }
}
