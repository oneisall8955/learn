package com.oneisall.learn.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oneisall.learn.helper.EnumCode;
import com.oneisall.learn.helper.KecDateDeserializer;
import com.oneisall.learn.helper.KecDateSerializer;
import com.oneisall.learn.java.common.Enum2Map;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * 枚举工具测试
 *
 * @author liam
 * @version V1.0
 * @date 2019/12/31
 **/
public class EnumTest {
    public enum Color implements EnumCode<Integer>, Enum2Map {
        RED("红色", 1),
        BLACK("黑色", 2);
        public final String text;
        public final int code;

        Color(String text, int code) {
            this.text = text;
            this.code = code;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public Map<String, Object> toMap() {
            return toMap(this.name(), code, text);
        }
    }

    public enum Fruit implements EnumCode<String>, Enum2Map {
        APPLE("苹果", "A"),
        BANANA("香蕉", "B"),
        MANGO("芒果", "M");
        public final String text;
        public final String code;

        Fruit(String text, String code) {
            this.text = text;
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public Map<String, Object> toMap() {
            return toMap(this.name(), code, text);
        }
    }

    public static class Foo {

        @JsonDeserialize(using = KecDateSerializer.EnumDeSerializer.class)
        @JsonSerialize(using = KecDateDeserializer.EnumSerializer.class)
        private Color color;

        @JsonDeserialize(using = KecDateSerializer.EnumDeSerializer.class)
        @JsonSerialize(using = KecDateDeserializer.EnumSerializer.class)
        private Fruit fruit;

        public Color getColor() {
            return color;
        }

        public Foo setColor(Color color) {
            this.color = color;
            return this;
        }

        public Fruit getFruit() {
            return fruit;
        }

        public Foo setFruit(Fruit fruit) {
            this.fruit = fruit;
            return this;
        }
    }

    @Test
    public void testSerializer() throws JsonProcessingException {
        Foo foo = new Foo().setColor(Color.BLACK);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = mapper.writeValueAsString(foo);
        System.out.println(json);
    }

    @Test
    public void testDeSerializer() throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Foo foo = mapper.readValue("{\"color\":1,\"fruit\":\"B\"}", Foo.class);
        System.out.println(foo.color);
        System.out.println(foo.fruit);
    }
}