package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Programmer;
import com.oneisall.learn.universal.design.pattern.decorator.ProgrammerDecorator;

/**
 * 前端UI工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class UIProgrammer extends ProgrammerDecorator {

    public UIProgrammer(Programmer programmer) {
        super(programmer);
    }

    @Override
    public String skill() {
        return "HTML和JS、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+300;
    }
}
