package com.oneisall.learn.universal.design.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 观察者
 *
 * @author : oneisall
 * @version : v1 2019/7/2 11:51
 */
@SuppressWarnings("all")
public class CartoonObserver implements Observer {

    private static Logger logger = LoggerFactory.getLogger(CartoonObserver.class);

    private String name;

    public CartoonObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof CartoonSubject) {
            String subjectName = subject.getName();
            String action = subject.getAction();
            CartoonSubject cartoonSubject = (CartoonSubject) subject;
            String cartoonContent = cartoonSubject.getCartoonContent();
            logger.info("{}订阅的主题:{},{}了,内容为:{}", this.name, subjectName, action, cartoonContent);
            logger.info("{}要去看了~", this.name);
            logger.info("{}表示{}真好看", this.name, cartoonContent);
        }
    }
}
