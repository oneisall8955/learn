package com.oneisall.learn.basic.enums;

/**
 * @author oneisall
 **/
public enum UserType {

    /**
     * 采购商
     */
    PURCHASER("采购商"),
    /**
     * 运营商
     */
    OPERATION("运营商"),
    /**
     * 供应商
     */
    SUPPLIER("供应商");

    public final String text;

    UserType(String text) {
        this.text = text;
    }

}
