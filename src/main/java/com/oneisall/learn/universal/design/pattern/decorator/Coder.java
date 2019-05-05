package com.oneisall.learn.universal.design.pattern.decorator;

/**
 * TODO :please describe it in one sentence
 *
 * @author oneisall
 * @version v1 2018/9/10 10:21
 */
public class Coder {

    String name;
    Programmer programmer;

    public Coder(String name) {
        this.name = name;
    }

    public Coder(String name, Programmer programmer) {
        this.name = name;
        this.programmer = programmer;
    }

    public void introduce() {
        int salary = programmer.salary();
        String skill = programmer.skill();
        System.out.println("我叫"+name+"，我会"+skill+"，我的理想薪水："+salary);
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }
}
