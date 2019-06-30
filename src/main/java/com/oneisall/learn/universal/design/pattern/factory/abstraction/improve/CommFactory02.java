package com.oneisall.learn.universal.design.pattern.factory.abstraction.improve;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

/**
 * 抽象工厂的改进2（反射+简单工厂）
 *
 * 使用反射的话，我们就可以不需要使用switch，因为使用switch的话，添加一个产品(Plane)的话，要switch的话又需要添加case条件。
 *
 * 我们可以根据 选择的品牌名称，如 “HONDA”， 利用反射技术自动的获得所需要的实例：
 *
 * 用一个字符串类型的 brand 变量来存储品牌名称，所以变量的值到底是 HONDA 还是 BENZ ，
 * 完全可以由事先设置的那个 brand 变量来决定，而我们又可以通过反射来去获取实例，这样就可以去除switch语句了。
 *
 * @author : oneisall
 * @version : v1 2019/6/30 17:44
 */
@SuppressWarnings("all")
public class CommFactory02 {

    private static String brand = "HONDA";
    private static String carPackageName;
    private static String shipPackageName;

    static {
        String brandFix = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        String basePackName = "com.oneisall.learn.universal.design.pattern.factory";
        carPackageName = basePackName + ".car." + brandFix + "Car";
        shipPackageName = basePackName + ".ship." + brandFix + "Ship";
    }

    public static Car createCar() {
        Car car = null;
        try {
            car = (Car) Class.forName(carPackageName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return car;
    }

    public static Ship createShip() {
        Ship ship = null;
        try {
            ship = (Ship) Class.forName(shipPackageName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ship;
    }
}
