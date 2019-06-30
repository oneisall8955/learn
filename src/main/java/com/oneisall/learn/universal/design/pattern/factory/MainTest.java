package com.oneisall.learn.universal.design.pattern.factory;

import com.google.common.collect.Lists;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.BenzFactory;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.BwmFactory;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.Factory;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.HondaFactory;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.improve.CommFactory;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.improve.CommFactory01;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.improve.CommFactory02;
import com.oneisall.learn.universal.design.pattern.factory.abstraction.improve.CommFactory03;
import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.car.CarBrand;
import com.oneisall.learn.universal.design.pattern.factory.general.BenzCarFactory;
import com.oneisall.learn.universal.design.pattern.factory.general.BwmCarFactory;
import com.oneisall.learn.universal.design.pattern.factory.general.HondaCarFactory;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;
import com.oneisall.learn.universal.design.pattern.factory.simple.SimpleCarFactory;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * 工厂模式测试
 *
 * @author : oneisall
 * @version : v1 2019/6/30 14:33
 */
public class MainTest {

    @Test
    public void simpleFactoryTest() {
        Car bmwCar = SimpleCarFactory.createCar(CarBrand.BMW);
        bmwCar.run();
        Car benzCar = SimpleCarFactory.createCar(CarBrand.BENZ);
        benzCar.run();
        Car hondaCar = SimpleCarFactory.createCar(CarBrand.HONDA);
        hondaCar.run();
    }

    @Test
    public void generalFactoryTest() {
        Car bmwCar = BwmCarFactory.getInstance().createCar();
        bmwCar.run();
        Car benzCar = BenzCarFactory.getInstance().createCar();
        benzCar.run();
        Car hondaCar = HondaCarFactory.getInstance().createCar();
        hondaCar.run();
    }

    @Test
    public void abstractFactoryTest() {
        Factory bwmInstance = BwmFactory.getInstance();
        Car bmwCar = bwmInstance.createCar();
        bmwCar.run();
        Ship bmwShip = bwmInstance.createShip();
        bmwShip.drive();

        System.out.println("-------------");

        Factory benzInstance = BenzFactory.getInstance();
        Car benzCar = benzInstance.createCar();
        benzCar.run();
        Ship benzShip = benzInstance.createShip();
        benzShip.drive();

        System.out.println("-------------");

        Factory hondaInstance = HondaFactory.getInstance();
        Car hondaCar = hondaInstance.createCar();
        hondaCar.run();
        Ship hondaShip = hondaInstance.createShip();
        hondaShip.drive();
    }

    @Test
    public void abstractFactoryImprove01Test() {
        Car car = CommFactory01.createCar();
        car.run();
        Ship ship = CommFactory01.createShip();
        ship.drive();
    }

    @Test
    public void abstractFactoryImprove02Test() {
        Car car = CommFactory02.createCar();
        car.run();
        Ship ship = CommFactory02.createShip();
        ship.drive();
    }

    @Test
    public void abstractFactoryImprove03Test() {
        Car car = CommFactory03.createCar();
        car.run();
        Ship ship = CommFactory03.createShip();
        ship.drive();
    }

    @Test
    public void abstractFactoryImprove04Test() {
        Random random = new Random();
        List<String> brandList = Lists.newArrayList("BWM", "HONDA", "BENZ");
        //模拟接口动态业务方法
        String brand = brandList.get(random.nextInt(brandList.size()));
        CommFactory commFactory = CommFactory.getInstance(brand);
        Car car = commFactory.createCar();
        car.run();
        Ship ship = commFactory.createShip();
        ship.drive();
    }
}
