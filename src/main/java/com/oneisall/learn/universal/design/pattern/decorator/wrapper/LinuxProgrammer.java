package com.oneisall.learn.universal.design.pattern.decorator.wrapper;


import com.oneisall.learn.universal.design.pattern.decorator.Programmer;
import com.oneisall.learn.universal.design.pattern.decorator.ProgrammerDecorator;

/**
 * 服务器工程师
 *
 * @author oneisall
 * @version v1 2018/9/10 10:01
 */
public class LinuxProgrammer extends ProgrammerDecorator {

    public LinuxProgrammer(Programmer programmer) {
        super(programmer);
    }

    @Override
    public String skill() {
        return "服务器运维、"+super.skill();
    }

    @Override
    public int salary() {
        return super.salary()+1500;
    }
}
