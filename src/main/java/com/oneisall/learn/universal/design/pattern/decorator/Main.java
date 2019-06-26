package com.oneisall.learn.universal.design.pattern.decorator;


import com.oneisall.learn.universal.design.pattern.decorator.wrapper.DbProfession;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.JavaProfession;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.LinuxProfession;
import com.oneisall.learn.universal.design.pattern.decorator.wrapper.FrontEndProfession;

/**
 * 测试
 *
 * @author oneisall
 * @version v1 2018/9/10 10:20
 */
public class Main {

    public static void main(String[] args) {

        Coder ming = new Coder("小明");
        Profession xmProfession = new JavaProfession(new OriginalProfession());
        ming.setProfession(xmProfession);
        ming.introduce();

        Coder hua = new Coder("小华");
        Profession xhProfession = new LinuxProfession(new DbProfession(new OriginalProfession()));
        hua.setProfession(xhProfession);
        hua.introduce();

        Coder god = new Coder("大神");
        Profession godProfession = new JavaProfession(new FrontEndProfession(new LinuxProfession(new DbProfession(new OriginalProfession()))));
        god.setProfession(godProfession);
        god.introduce();

    }
}
