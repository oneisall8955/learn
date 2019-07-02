package com.oneisall.learn.universal.design.pattern.observer;

/**
 * 卡通主题
 *
 * @author : oneisall
 * @version : v1 2019/7/2 11:52
 */
public class CartoonSubject extends AbstractSubject {

    private String cartoonContent;

    public CartoonSubject(String subjectName) {
        super(subjectName);
    }

    public void setCartoonContent(String cartoonContent) {
        this.cartoonContent = cartoonContent;
    }

    public String getCartoonContent() {
        return cartoonContent;
    }
}
