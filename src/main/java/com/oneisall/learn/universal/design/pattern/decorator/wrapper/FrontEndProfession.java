package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Profession;
import com.oneisall.learn.universal.design.pattern.decorator.AbstractProfessionDecorator;

/**
 * 前端UI工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class FrontEndProfession extends AbstractProfessionDecorator {

    public FrontEndProfession(Profession profession) {
        super(profession);
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
