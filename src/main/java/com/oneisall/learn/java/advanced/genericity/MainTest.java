package com.oneisall.learn.java.advanced.genericity;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型Demo
 *
 * @author oneisall
 * @version v1 2019/4/11 14:11
 */
public class MainTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testGeneric() {

        logger.info("泛型测试key is {}", new Generic<>(123456).getKey());
        logger.info("泛型测试key is {}", new Generic<>("key_value").getKey());
        logger.info("泛型测试key is {}", new Generic<>(55.55).getKey());
        logger.info("泛型测试key is {}", new Generic<>(false).getKey());

        //错误的 instanceof 使用
        //Generic<Boolean> booleanGeneric = new GenericTest<>(true);
        //boolean b = booleanGeneric instanceof Generic<Boolean>;

        FruitGenerator<Boolean> fruitGenerator1 = new FruitGenerator<>();
        //null
        Boolean next1 = fruitGenerator1.next();
        logger.info("泛型接口非具体实现 next is {}", next1);

        FruitGenerator2 f2 = new FruitGenerator2();
        String next2 = f2.next();
        logger.info("泛型接口具体实现 next is {}", next2);

        FruitGenerator3<Boolean> boolean3 = new FruitGenerator3<>(false);
        Generic<Boolean> next3 = boolean3.next();
        Boolean nextKey = next3.getKey();
        logger.info("泛型接口+泛型类(具体实现类为Generic<T>) next.key is {}", nextKey);

        next3.showKeyName(next3);
        next3.showKeyName2(boolean3);

        /////////////////////////////////////////////////////////

        Generic<Father> fatherGeneric = new Generic<>(new Father());
        showFatherOnly(fatherGeneric);

        Generic<Son> sonGeneric = new Generic<>(new Son());
        // sonGeneric.getKey().intValue();
        // 错误的泛型使用,与多态进行区别!!!
        // 首先,多态接口中,某Object obj有方法method(Father father) 可以调用 son =new Son();obj.method(son),
        // 但是,泛型接口中,某Object obj有方法method(Generic<Father> fatherGeneric) 不可以调用 GenericTest<Son> sonGeneric =new GenericTest(new Son()); obj.method(sonGeneric),
        //
        // showFatherOnly(sonGeneric);
        // fatherGeneric == sonGeneric;
        Generic<Object> objectGeneric = new Generic<>(new Object());
        //错误使用*1
        //showFatherOnly(objectGeneric);

        //解决办法1 使用<?> 缺点:无法候取父类进行多态操作,只使用Object类中的功能
        showAll(fatherGeneric);
        showAll(sonGeneric);

        //解决办法2 使用<? extends Father>
        showExtendFather(fatherGeneric);
        showExtendFather(sonGeneric);

        //注意点
        //错误使用*2
        //showFatherOnly(objectGeneric);
        showSuperFather(objectGeneric);


    }

    /**
     * 接收所有类
     */
    private void showAll(Generic<?> obj) {
        logger.info("泛型通配符测试 key is {}", obj.getKey());
    }

    /**
     * 仅仅接收Father对象
     */
    private void showFatherOnly(Generic<Father> obj) {
        logger.info("泛型通配符测试 key is {}", obj.getKey().name());
    }

    /**
     * 设置上限 只能接收Father及其Father的子类
     */
    private void showExtendFather(Generic<? extends Father> obj) {
        logger.info("泛型通配符测试 key is {}", obj.getKey().name());
    }

    /**
     * 设置下限 只能接收Number及Object类
     */
    private void showSuperFather(Generic<? super Father> obj) {
        // 只能使用Object的API
        logger.info("泛型通配符测试 key is {}", obj.getKey());
    }

    //////////////////////////////

    private static class Father {
        String name = "FATHER.name";

        String name() {
            return name;
        }

        @Override
        public String toString() {
            return "重写Object的FATHER";
        }
    }

    private static class Son extends Father {
        Son() {
            super.name = "SON.name";
        }

        @Override
        public String toString() {
            return "重写Object的SON";
        }
    }
}
