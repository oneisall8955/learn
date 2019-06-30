package com.oneisall.learn.universal.design.pattern.factory.abstraction.improve;

import com.oneisall.learn.universal.design.pattern.factory.car.BenzCar;
import com.oneisall.learn.universal.design.pattern.factory.car.BwmCar;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.car.HondaCar;
import com.oneisall.learn.universal.design.pattern.factory.ship.BenzShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.BwmShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.HondaShip;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 抽象工厂模式的改进1。（简单工厂+抽象工厂）
 *
 * @author : oneisall
 * @version : v1 2019/6/30 17:44
 */
public class CommFactory01 {

    private static String brand = "BENZ";

    public static Car createCar() {

        Car car = null;
        switch (brand) {
            case "BENZ":
                car = new BenzCar();
                break;
            case "HONDA":
                car = new HondaCar();
                break;
            case "BWM":
                car = new BwmCar();
                break;
            default:
                //记录或抛出异常
        }
        return car;
    }

    public static Ship createShip() {

        Ship ship = null;
        switch (brand) {
            case "BENZ":
                ship = new BenzShip();
                break;
            case "HONDA":
                ship = new HondaShip();
                break;
            case "BWM":
                ship = new BwmShip();
                break;
            default:
                //记录或抛出异常
        }
        return ship;
    }


}
