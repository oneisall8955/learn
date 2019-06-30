package com.oneisall.learn.universal.design.pattern.factory.abstraction.improve;

import com.oneisall.learn.universal.design.pattern.factory.car.Car;
import com.oneisall.learn.universal.design.pattern.factory.ship.Ship;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 抽象工厂的改进3（反射+配置文件+简单工厂）
 * <p>
 * 在使用反射之后，我们还是需要进 CommFactory2.java 中修改品牌名称，还不是完全符合开-闭原则。
 * <p>
 * 我们可以通过配置文件来达到目的，每次通过读取配置文件来知道我们应该使用哪种品牌名称。
 *
 * @author : oneisall
 * @version : v1 2019/6/30 17:44
 */
public class CommFactory03 {

    private static String carPackageName;
    private static String shipPackageName;

    static {
        Properties properties = new Properties();
        try {
            InputStream applicationIn = Class.forName(CommFactory03.class.getName()).getResourceAsStream("/application.properties");
            properties.load(new InputStreamReader(applicationIn, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String brand = properties.get("design.pattern.factory.abstraction.improve.brand").toString();
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
