package com.oneisall.learn.base.enums;

import com.oneisall.learn.common.CommonResult;
import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oneisall
 */
public class MainTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    enum Foo {
        A("Foo-A", FooBar.A),
        B("Foo-B", FooBar.B),
        C("Foo-C", FooBar.C),
        ;
        public String text;
        public FooBar fooBar;

        Foo(String text, FooBar fooBar) {
            this.text = text;
            this.fooBar = fooBar;
        }

        public Bar bar;

        static {
            Foo.A.bar = Bar.A;
            Foo.B.bar = Bar.B;
            Foo.C.bar = Bar.C;
        }
    }

    enum Bar {
        A("Bar-A", FooBar.A),
        B("Bar-B", FooBar.B),
        C("Bar-C", FooBar.C),
        ;
        public String text;
        public FooBar fooBar;

        Bar(String text, FooBar fooBar) {
            this.text = text;
            this.fooBar = fooBar;
        }

        public Foo foo;

        static {
            Bar.A.foo = Foo.A;
            Bar.B.foo = Foo.B;
            Bar.C.foo = Foo.C;
        }
    }

    enum FooBar {
        A("FooBar-A", Foo.A, Bar.A),
        B("FooBar-B", Foo.B, Bar.B),
        C("FooBar-C", Foo.C, Bar.C),
        ;
        public String text;
        public Foo foo;
        public Bar bar;

        FooBar(String text, Foo foo, Bar bar) {
            this.text = text;
            this.foo = foo;
            this.bar = bar;
        }
    }

    @Test
    public void EnumTest1() {
        Foo foo;
        Bar bar;
        FooBar fooBar;
        for (Foo item : Foo.values()) {
            foo = item;
            bar = foo.bar;
            fooBar = foo.fooBar;
            logger.info("Foo's text:{},bar:{},fooBar:{}", foo.text, Optional.ofNullable(bar).map(e -> e.text).orElse(null), Optional.ofNullable(fooBar).map(e -> e.text).orElse(null));
        }
        for (Bar item : Bar.values()) {
            bar = item;
            foo = bar.foo;
            fooBar = bar.fooBar;
            logger.info("Bar's text:{},foo:{},fooBar:{}", bar.text, Optional.ofNullable(foo).map(e -> e.text).orElse(null), Optional.ofNullable(fooBar).map(e -> e.text).orElse(null));
        }
        for (FooBar item : FooBar.values()) {
            fooBar = item;
            foo = fooBar.foo;
            bar = fooBar.bar;
            logger.info("FooBar's text:{},foo:{},bar:{}", fooBar.text, Optional.ofNullable(foo).map(e -> e.text).orElse(null), Optional.ofNullable(bar).map(e -> e.text).orElse(null));
        }

    }

    @Test
    public void EnumTest2() {
        OrderStatus[] statuses = OrderStatus.values();
        UserType[] userTypes = UserType.values();
        Operation[] operations = Operation.values();

        Map<OrderStatus, Map<UserType, List<Operation>>> allowMap = new LinkedHashMap<>();

        for (OrderStatus status : statuses) {
            Map<UserType, List<Operation>> allowUserTypeMap = new LinkedHashMap<>();
            for (UserType userType : userTypes) {
                List<Operation> allowOptionList = new ArrayList<>();
                for (Operation operation : operations) {
                    CommonResult result = status.allowOperate(userType, operation);
                    System.out.println(result.getMsg());
                    if (result.isStatus()) {
                        allowOptionList.add(operation);
                    }
                }
                allowUserTypeMap.put(userType, allowOptionList);
                System.out.println("----------------------------");
            }
            allowMap.put(status, allowUserTypeMap);
        }
        for (Map.Entry<OrderStatus, Map<UserType, List<Operation>>> entry : allowMap.entrySet()) {
            OrderStatus key = entry.getKey();
            Map<UserType, List<Operation>> value = entry.getValue();
            for (Map.Entry<UserType, List<Operation>> entry1 : value.entrySet()) {
                UserType key1 = entry1.getKey();
                List<Operation> value1 = entry1.getValue();
                String join = StringUtils.join(value1.stream().map(operation -> operation.text).collect(Collectors.toList()), ",");
                System.out.println("退废单状态:[" + key.text + "],角色[" + key1.text + "],允许操作:" + join);
            }
            System.out.println("-");
        }
        System.out.println("----------------------------");
    }
}
