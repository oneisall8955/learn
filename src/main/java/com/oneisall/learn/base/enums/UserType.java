package com.oneisall.learn.base.enums;

/**
 * @description: 用户类型
 * @create: 2019/1/14 15:00
 **/
public enum UserType {

    ALL("全部"),
    PURCHASER("采购商"),
    OPERATION("运营商"),
    SUPPLIER("供应商");

    public final String text;

    UserType(String text) {
        this.text = text;
    }

}
