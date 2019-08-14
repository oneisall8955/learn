package com.oneisall.learn.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型测试
 *
 * 参考:
 * <p></p>
 * 博客园:RainDream:<? extends T>和<? super T>:https://www.cnblogs.com/drizzlewithwind/p/6100164.html
 * <p></p>
 * 并发编程网: 泛型中? super T和? extends T的区别 : http://ifeve.com/difference-between-super-t-and-extends-t-in-java/
 * <p></p>
 * stackoverflow:Difference between <? super T> and <? extends T> in Java [duplicate]:https://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
 * @author : oneisall
 * @version : v1 2019/8/13 09:29
 */
@SuppressWarnings("all")
public class GenericTest {

    class Food {
        protected String name = "Fruit";

        public void echo() {
            System.out.println(name);
        }
    }

    class Fruit extends Food {

    }

    class Apple extends Fruit {
        public Apple() {
            name = "Apple";
        }
    }

    class Pear extends Fruit {
        public Pear() {
            name = "Pear";
        }
    }

    class Plate<T> {
        private T item;

        public Plate() {

        }

        public Plate(T item) {
            this.item = item;
        }

        public void set(T t) {
            item = t;
        }

        public T get() {
            return item;
        }

        public void methodWithT(T t) {
            System.out.println("methodWithT,T's is : " + t.toString());
        }
    }

    /**
     * 检测普通泛型容器API
     */
    @Test
    public void plateSelfTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();

        // 父类容器,放置子类
        // 此处是多态的提供的API
        // apple is a fruit
        fruitPlate.set(apple);
        fruitPlate.methodWithT(apple);

        // 父类容器与子类容器的关系,此处关注的是容器这个类!!!
        // 并没有像父子类中的继承关系!!!
        // apple's plate is not a fruit's plate
        // ERROR
        // fruitPlate = applePlate;
        // ERROR
        // applePlate = fruitPlate;

        // 为解决此类容器间'继承多态'问题,使用了<? extends T>及<? supper T>
    }

    /**
     * <? extends T> 上界通配符(Upper Bounds Wildcards)
     * <p>
     * Plate <? extends Fruit> extendsFruitPlate 可以被 Plate <Fruit> 及 Plate <Apple> 赋值
     */
    @Test
    public void extendsTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();
        Plate<? extends Fruit> extendsFruitPlate = new Plate<>();

        // ERROR
        // fruitPlate = applePlate;

        // SUCCESS
        // Plate<? extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类
        extendsFruitPlate = applePlate;
        extendsFruitPlate = fruitPlate;

        // ERROR
        // 反过来明显是错误的!
        // applePlate = extendsFruitPlate;
        // fruitPlate = extendsFruitPlate;
    }

    /**
     * <? supper T> 下界通配符(Lower Bounds Wildcards)
     * Plate <? supper Fruit> superFruitPlate 可以被 Plate <Fruit> 及 Plate <Object> 赋值
     */
    @Test
    public void supperTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Apple> applePlate = new Plate<>();
        Plate<Object> objectPlate = new Plate<>();
        Plate<? super Fruit> superFruitPlate = new Plate<>();

        // ERROR
        // superFruitPlate = applePlate;
        // SUCCESS
        superFruitPlate = fruitPlate;
        superFruitPlate = objectPlate;

        // ERROR
        // 反过来明显是错误的!
        // fruitPlate = superFruitPlate;
        // objectPlate = superFruitPlate;
    }

    /**
     * <? extends T> 上界通配符(Upper Bounds Wildcards)
     * 注意点: 只能获取,不能存放
     */
    @Test
    public void extendsAttentionTest() {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        Pear pear = new Pear();

        Plate<? extends Fruit> extendsFruitPlate;
        extendsFruitPlate = new Plate<Fruit>(fruit);
        // 注:此处运用的是多态,有参构造函数中 Plate(Fruit item){ ... } 的item类型多态
        extendsFruitPlate = new Plate<Apple>(apple);
        extendsFruitPlate = new Plate<Pear>(pear);

        // <? extends T> 容器的API注意点:不能往里存,只能往外取
        // Attention: 意思是:泛类实例用<? extends T>修饰,该泛类的API中,
        // 方法形如 method(T t)均不能再被使用.
        // 方法形如 T method(XX xx)均可以调用(XX xx代表任何确定的类型或者无参)
        // '不能往里存，只能往外取',这种描述不是很准确,只是比较通俗易懂.
        // ERROR:
        // extendsFruitPlate.set(fruit);
        // extendsFruitPlate.set(apple);
        // extendsFruitPlate.set(pear);
        // extendsFruitPlate.set(new Object());
        // extendsFruitPlate.methodWithT(fruit);
        // extendsFruitPlate.methodWithT(apple);
        // extendsFruitPlate.methodWithT(pear);
        // extendsFruitPlate.methodWithT(new Object());

        // SUCCESS
        // 获取到具体的实例是Apple类型
        Fruit getFruit = extendsFruitPlate.get();
        getFruit.echo();

        System.out.println("--------------");

        List<Fruit> fruits = Lists.newArrayList(fruit);
        List<Apple> apples = Lists.newArrayList(apple);
        List<? extends Fruit> extensFruitList;
        // 可以被 List<Fruit> 及 List<Apple> 赋值
        extensFruitList = fruits;
        extensFruitList = apples;
        // 原理一样,使用List重现
        // ERROR:调用 add(T t)
        // extensFruitList.add(fruit);
        // extensFruitList.add(apple);
        // extensFruitList.add(new Object());

        // SUCCESS:调用 T get(int index)
        Fruit getFruitByList = extensFruitList.get(0);
        getFruitByList.echo();
        System.out.println("--------------");

        // 接口测试
        class ExtendsClass {
            public void extendsMethod(List<? extends Fruit> extendsList) {
                // ERROR:
                // 出错原理同上,不能调用形如 method(T t)方法
                // extendsList.add(fruit);
                // extendsList.add(apple);
                // extendsList.add(new Object());
                // SUCCESS
                // 获取是父类,可以强转为子类再使用
                Fruit getFruitByList = extendsList.get(0);
                getFruitByList.echo();
            }
        }
        ExtendsClass extendsClass = new ExtendsClass();
        extendsClass.extendsMethod(fruits);
        extendsClass.extendsMethod(apples);

        List<Object> objects = Lists.newArrayList(new Object());
        // ERROR
        // 明显无法调用
        // extendsClass.extendsMethod(objects);

        /**
         总结:<? extends T>修饰的泛类实例,为什么可以调用形如 T method(XX xx),却不能调用 形如 method(T t)的方法?
         使用List接口例子理解:

         List<Fruit> fruits = Lists.newArrayList(new Fruit());
         List<Apple> apples = Lists.newArrayList(new Apple());
         List<Pear> pears = Lists.newArrayList(new Pear());
         List<? extends Fruit> extensFruitList;
         extensFruitList = fruits;
         extensFruitList = apples;
         extensFruitList = pears;

         编译器的理解: List<? extends Fruit> extensFruitList 这个 List ,
         你可以读取到 Fruit ,因为 extensFruitList 要么包含 Fruit 实例,要么包含 Fruit 的子类实例.

         你不能保证读取到 Apple ,因为 extensFruitList 可能指向的是 List<Fruit>
         你不能保证读取到 Pear ,因为 extensFruitList 可能指向的是 List<Apple>

         你不能插入一个 Fruit 元素，因为 extensFruitList 可能指向 List<Apple> 或 List<Pear>
         你不能插入一个 Apple 元素，因为 extensFruitList 可能指向 List<Fruit> 或 List<Pear>
         你不能插入一个 Pear 元素，因为 extensFruitList 可能指向 List<Fruit> 或 List<Apple>

         你不能往List<? extends T>中插入任何类型的对象,因为你不能保证列表实际指向的类型是什么,
         你并不能保证列表中实际存储什么类型的对象,唯一可以保证的是,你可以从中读取到T或者T的子类.

         所以,可以调用形如 T method(XX xx)的接口extensFruitList.get(0) 获取 Fruit 的实例
         却不能调用形如 method(T t)的接口extensFruitList.add(new Fruit())添加元素
         也却不能调用形如 method(T t)的接口extensFruitList.set(0,new Fruit())替换对象

         所以,当你理解以上编译器的规则,我们自定义的Plate泛类的例子自然也能理解了:
         Plate<? extends Fruit> extendsFruitPlate;
         extendsFruitPlate = new Plate<Fruit>(fruit);
         extendsFruitPlate = new Plate<Apple>(apple);
         extendsFruitPlate = new Plate<Pear>(pear);
         // ERROR:
         // extendsFruitPlate.set(apple);
         // extendsFruitPlate.methodWithT(apple);
         extendsFruitPlate可能指向 Plate<Pear> , 此时以上调用等价于
         Plate<Pear> pearPlate=new Plate<Pear>();
         pearPlate.set(apple);
         pearPlate.methodWithT(apple);
         明显类型不相同,且不符合多态,导致编译异常

         /// ERROR:
         // extendsFruitPlate.set(pear);
         // extendsFruitPlate.methodWithT(pear);
         extendsFruitPlate可能指向 Plate<Apple> , 此时以上调用等价于
         Plate<Apple> pearPlate=new Plate<Apple>();
         pearPlate.set(pear);
         pearPlate.methodWithT(pear);
         明显类型不相同,且不符合多态,导致编译异常
         * */
    }

    /**
     * <? supper T> 下界通配符(Lower Bounds Wildcards)
     * 注意点: 取出是Object,存放是父类或子类
     */
    @Test
    public void superAttentionTest() {
        Object object = new Object();
        Food food = new Food();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        Pear pear = new Pear();

        Plate<? super Fruit> superFruitPlate;
        // 可以被 Plate<Object> , Plate<Food> ,Plate<Fruit> Plate<父类容器> 赋值
        superFruitPlate = new Plate<Object>(object);
        superFruitPlate = new Plate<Food>();
        superFruitPlate = new Plate<Fruit>();
        // ERROR 非Fruit及Fruit父类容器赋值编译不通过
        // superFruitPlate = new Plate<Apple>();
        // superFruitPlate = new Plate<Pear>();

        // SUCCESS
        superFruitPlate.set(fruit);
        superFruitPlate.set(apple);
        superFruitPlate.set(pear);
        superFruitPlate.methodWithT(fruit);
        superFruitPlate.methodWithT(apple);
        superFruitPlate.methodWithT(pear);
        // ERROR:调用method(T t)时候,当t[不是 T或T的子类时],则编译异常
        // superFruitPlate.set(food);
        // superFruitPlate.set(object);
        // superFruitPlate.methodWithT(food);
        // superFruitPlate.methodWithT(object);

        System.out.println("=======================");


        // 只能获取到Object对象,需要进行强转才可以进行调用相关API
        Object supperFruitPlateGet = superFruitPlate.get();
        if (supperFruitPlateGet instanceof Fruit) {
            // 为什么需要 instanceof ? superFruitPlate可以指向Plate<Object>,获取出来是Object
            Fruit convertFruit = (Fruit) supperFruitPlateGet;
            convertFruit.echo();
        }

        // 使用List重现
        List<Object> objects = new ArrayList<>(Arrays.asList(object));
        List<Food> foods = new ArrayList<>(Arrays.asList(food));
        List<Fruit> fruits = new ArrayList<>(Arrays.asList(fruit));
        List<Apple> apples = new ArrayList<>(Arrays.asList(apple));
        List<Pear> pears = new ArrayList<>(Arrays.asList(pear));
        List<? super Fruit> superFruitList;
        // 可以被 List<Object> , List<Food> ,List<Fruit> List<父类容器> 赋值
        superFruitList = fruits;
        superFruitList = foods;
        superFruitList = objects;
        // ERROR 非Fruit及Fruit父类容器则编译不通过
        // superFruitList = apples;
        // superFruitList = pears;

        // SUCCESS 添加
        superFruitList.add(fruit);
        superFruitList.add(apple);
        superFruitList.add(pear);
        // 原理一样,使用List重现
        // ERROR:调用add(T t)时候,当t[不是 Fruit或Fruit的子类时],则编译异常
        // superFruitList.add(object);
        // superFruitList.add(food);


        // SUCCESS:调用 T get(int index),只能获取到Object对象,需要进行强转才可以进行调用相关API
        Object getByFruitPlate = superFruitList.get(0);
        if (getByFruitPlate instanceof Fruit) {
            // 为什么需要 instanceof ? superFruitList可以指向List<Object> objects,获取出来是Object
            Fruit convertFruit = (Fruit) getByFruitPlate;
            convertFruit.echo();
        } else {
            System.out.println("非Fruit,插入非本类或非子类:" + getByFruitPlate);
        }

        System.out.println("=======================");

        // 接口测试
        class SuperClass {
            public void supperMethod(List<? super Fruit> superList) {

                superList.add(fruit);
                superList.add(apple);
                superList.add(pear);
                // ERROR:原因如上,调用method(T t)时候,当t[不是 T或T的子类时],则编译异常
                // superList.add(object);
                // superList.add(food);
                Object innerObject = superList.get(0);
                if (innerObject instanceof Fruit) {
                    // 为什么需要 instanceof ? 像这样:superFruitPlate 可以指向List<Object> objects,获取出来是Object
                    Fruit innerConvertFruit = (Fruit) innerObject;
                    innerConvertFruit.echo();
                } else {
                    System.out.println("SuperClass,supperMethod:非Fruit,插入非本类或非子类:" + innerObject);
                }
            }
        }
        SuperClass superClass = new SuperClass();
        superClass.supperMethod(objects);
        superClass.supperMethod(foods);
        superClass.supperMethod(fruits);
        // ERROR 原因同上,非Fruit及Fruit父类容器则编译不通过
        // superClass.supperMethod(apples);
        // superClass.supperMethod(pears);

        /**
         总结:<? super T>修饰的泛类实例,
         为什么读取的时候,为什么是Object类型? 调用形如 method(T t)时候,t只能是T或T的子类,否则编译异常 ?

         使用List接口例子理解:

         List<Object> objects = new ArrayList<>(Arrays.asList(object));
         List<Food> foods = new ArrayList<>(Arrays.asList(food));
         List<Fruit> fruits = new ArrayList<>(Arrays.asList(fruit));
         List<? super Fruit> superFruitList;
         // 可以被 List<Object> , List<Food> ,List<Fruit> List<父类容器> 赋值
         superFruitList = fruits;
         superFruitList = foods;
         superFruitList = objects;

         编译器的理解: List<? super Fruit> superFruitList 这个 List ,
         superFruitList不能确保读取到Fruit,因为superFruitList可能指向List<Object> objects或List<Food> foods
         superFruitList不能确保读取到Food,因为superFruitList可能指向List<Object>
         所以,取出来必须是[Object],最后需要则调用强转

         你不能插入一个 Food 元素，因为 superFruitList 可能指向 List<Fruit>
         你不能插入一个 Object 元素，因为 superFruitList 可能指向 List<Fruit> 或 List<Food>

         你可以插入一个 Fruit/Apple/Pear 类型的元素,因为 Fruit/Apple/Pear 类都是 Fruit,Food,Object的本身或子类

         所以,你从superFruitList获取到的都是Object对象;调用形如 method(T t)时候,t只能是T或T的子类,否则编译异常

         所以,当你理解以上编译器的规则,我们自定义的Plate泛类的例子自然也能理解了:
         Plate<? super Fruit> superFruitPlate;
         superFruitPlate = new Plate<Object>(object);
         superFruitPlate = new Plate<Food>(food);
         superFruitPlate = new Plate<Fruit>(fruit);
         // ERROR:
         // superFruitPlate.set(object);
         // superFruitPlate.methodWithT(object);
         superFruitPlate 可能指向 Plate<Food> , 此时以上调用等价于
         Plate<Food> pearPlate=new Plate<Food>();
         pearPlate.set(object);
         pearPlate.methodWithT(object);
         明显类型不相同,且不符合多态,导致编译异常

         // ERROR:
         // superFruitPlate.set(food);
         // superFruitPlate.methodWithT(food);
         superFruitPlate 可能指向 Plate<Fruit> , 此时以上调用等价于
         Plate<Food> pearPlate=new Plate<Fruit>();
         pearPlate.set(food);
         pearPlate.methodWithT(food);
         明显类型不相同,且不符合多态,导致编译异常
         * */

    }


}
