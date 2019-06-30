package com.oneisall.learn.universal.design.pattern.factory.abstraction.improve;

import com.oneisall.learn.universal.design.pattern.factory.abstraction.Factory;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

import java.lang.reflect.Method;

/**
 * 自动化工厂,个人认为开发中最合适的方式
 *
 * @author : oneisall
 * @version : v1 2019/6/30 17:44
 */
public class CommFactory implements Factory {

    private String carPackageName;
    private String shipPackageName;

    private CommFactory() {

    }

    public static CommFactory getInstance(String brand) {
        String brandFix = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        String basePackName = "com.oneisall.learn.universal.design.pattern.factory";
        CommFactory commFactory = new CommFactory();
        commFactory.carPackageName = basePackName + ".car." + brandFix + "Car";
        commFactory.shipPackageName = basePackName + ".ship." + brandFix + "Ship";
        return commFactory;
    }

    public static Factory getInstanceNotRecommend(String brand) {
        String brandFix = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        String basePackName = "com.oneisall.learn.universal.design.pattern.factory.abstraction";
        String factoryPackageName = basePackName + "." + brandFix + "Factory";
        Factory factory = null;
        try {
            Class<?> factoryClass = Class.forName(factoryPackageName);
            Method method = factoryClass.getDeclaredMethod("getInstance");
            Object invoke = method.invoke(null);
            factory = (Factory) invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    @Override
    public Car createCar() {
        Car car = null;
        try {
            car = (Car) Class.forName(this.carPackageName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public Ship createShip() {
        Ship ship = null;
        try {
            ship = (Ship) Class.forName(this.shipPackageName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ship;
    }
}
