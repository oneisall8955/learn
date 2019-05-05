package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Programmer;
import com.oneisall.learn.universal.design.pattern.decorator.ProgrammerDecorator;

/**
 * JAVA程序员
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class JavaProgrammer extends ProgrammerDecorator {

    public JavaProgrammer(Programmer programmer) {
        super(programmer);
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
