package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Profession;
import com.oneisall.learn.universal.design.pattern.decorator.AbstractProfessionDecorator;

/**
 * JAVA程序员
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class JavaProfession extends AbstractProfessionDecorator {

    public JavaProfession(Profession profession) {
        super(profession);
    }

    @Override
    public String skill() {
        return "JVM调优、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+500;
    }
}
