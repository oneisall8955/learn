package com.oneisall.learn.universal.design.pattern.decorator;


import com.oneisall.learn.universal.design.pattern.decorator.wrapper.DbProgrammer;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.JavaProgrammer;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.LinuxProgrammer;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.UIProgrammer;

/**
 * 测试
 *
 * @author oneisall
 * @version v1 2018/9/10 10:20
 */
public class Main {

    public static void main(String[] args) {

        Coder xiaoming = new Coder("小明");
        Programmer xmProgrammer = new JavaProgrammer(new OriginalProgrammer());
        xiaoming.setProgrammer(xmProgrammer);
        xiaoming.introduce();

        Coder xiaohua = new Coder("小华");
        Programmer xhProgrammer = new LinuxProgrammer(new DbProgrammer(new OriginalProgrammer()));
        xiaohua.setProgrammer(xhProgrammer);
        xiaohua.introduce();

        Coder god = new Coder("大神");
        Programmer godProgrammer = new JavaProgrammer(new UIProgrammer(new LinuxProgrammer(new DbProgrammer(new OriginalProgrammer()))));
        god.setProgrammer(godProgrammer);
        god.introduce();

    }
}
