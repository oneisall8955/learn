package com.oneisall.learn.java.advanced.annotation;

/**
 * 人
 *
 * @author : oneisall
 * @version : v1 2019/5/28 09:44
 */
@Builder
public class People {
    @Custom
    private String name;
    @Custom(value = "广州")
    private String address;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
