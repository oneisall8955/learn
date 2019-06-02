package com.oneisall.learn.java.basic.enums;

import com.oneisall.learn.java.common.Result;
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
        A("Foo-A"),
        B("Foo-B"),
        C("Foo-C"),
        ;
        public String text;

        Foo(String text) {
            this.text = text;
        }

        public Bar bar;

        static {
            A.bar = Bar.A;
            B.bar = Bar.B;
            C.bar = Bar.C;
        }
    }

    enum Bar {
        A("Bar-A"),
        B("Bar-B"),
        C("Bar-C"),
        ;
        public String text;

        Bar(String text) {
            this.text = text;
        }

        public Foo foo;

        static {
            A.foo = Foo.A;
            B.foo = Foo.B;
            C.foo = Foo.C;
        }
    }

    enum Qux {
        A("Qux-A", Baz.A),
        B("Qux-B", Baz.B),
        C("Qux-C", Baz.C),
        ;
        public String text;
        public Baz baz;

        Qux(String text, Baz baz) {
            this.text = text;
            this.baz = baz;
        }
    }

    enum Baz {
        A("Baz-A", Qux.A),
        B("Baz-B", Qux.B),
        C("Baz-C", Qux.C),;
        public String text;
        public Qux qux;

        Baz(String text, Qux qux) {
            this.text = text;
            this.qux = qux;
        }
    }

    @Test
    public void enumTest1() {
        Foo foo;
        Bar bar;
        for (Foo item : Foo.values()) {
            foo = item;
            bar = foo.bar;

            logger.info("Foo 's text:{},bar:{}", foo.text, Optional.ofNullable(bar).map(e -> e.text).orElse(null));
        }
        logger.info("------------------");
        for (Bar item : Bar.values()) {
            bar = item;
            foo = bar.foo;
            logger.info("Bar 's text:{},foo:{}", bar.text, Optional.ofNullable(foo).map(e -> e.text).orElse(null));
        }
        logger.info("------------------");
        Qux qux;
        Baz baz;
        for (Qux item : Qux.values()) {
            qux = item;
            baz = qux.baz;

            logger.info("Qux 's text:{},Baz:{}", qux.text, Optional.ofNullable(baz).map(e -> e.text).orElse(null));
        }
        logger.info("------------------");
        for (Baz item : Baz.values()) {
            baz = item;
            qux = baz.qux;
            logger.info("Baz 's text:{},qux:{}", baz.text, Optional.ofNullable(qux).map(e -> e.text).orElse(null));
        }
    }

    @Test
    public void enumTest2() {
        OrderStatus[] statuses = OrderStatus.values();
        UserType[] userTypes = UserType.values();
        Operation[] operations = Operation.values();

        Map<OrderStatus, Map<UserType, List<Operation>>> allowMap = new LinkedHashMap<>();

        for (OrderStatus status : statuses) {
            Map<UserType, List<Operation>> allowUserTypeMap = new LinkedHashMap<>();
            for (UserType userType : userTypes) {
                List<Operation> allowOptionList = new ArrayList<>();
                for (Operation operation : operations) {
                    Result result = status.allowOperate(userType, operation);
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
