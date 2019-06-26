package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * 码农
 *
 * @author oneisall
 * @version v1 2018/9/10 10:21
 */
public class Coder {

    /** 码农姓名*/
    private String name;
    /** 职业技能*/
    private Profession profession;

    Coder(String name) {
        this.name = name;
    }

    public Coder(String name, Profession profession) {
        this.name = name;
        this.profession = profession;
    }

    void introduce() {
        int salary = profession.salary();
        String skill = profession.skill();
        System.out.println("我叫"+name+"，我会"+skill+"，我的理想薪水："+salary);
    }

    void setProfession(Profession profession) {
        this.profession = profession;
    }
}
