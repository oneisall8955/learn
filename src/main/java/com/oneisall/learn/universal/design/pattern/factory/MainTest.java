package com.oneisall.learn.universal.design.pattern.factory;

import com.oneisall.learn.universal.design.pattern.factory.simple.SimpleCarFactory;
import org.junit.Test;

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
}
