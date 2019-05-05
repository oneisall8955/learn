package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Programmer;
import com.oneisall.learn.universal.design.pattern.decorator.ProgrammerDecorator;

/**
 * 数据库工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class DbProgrammer extends ProgrammerDecorator {

    public DbProgrammer(Programmer programmer) {
        super(programmer);
    }

    @Override
    public String skill() {
        return "SQL 调优、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+1000;
    }
}
