package com.oneisall.learn.universal.design.pattern.observer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 订阅多种的主题
 *
 * @author : oneisall
 * @version : v1 2019/7/2 15:12
 */
@SuppressWarnings("all")
public class VarietyObserver implements Observer {

    private static Logger logger = LoggerFactory.getLogger(VarietyObserver.class);

    private String name;

    private List<Observer> delegate = new ArrayList<>();
    private List<Consumer<Subject>> consumers = new ArrayList<>();

    public VarietyObserver(String name) {
        this.name = name;
        delegate.add(new StoryObserver(name));
        delegate.add(new CartoonObserver(name));
        init();
    }

    private void init() {
        Consumer<Subject>  cartoonConsumer= (subject) -> {
            if (subject instanceof CartoonSubject) {
                String subjectName = subject.getName();
                String action = subject.getAction();
                CartoonSubject cartoonSubject = (CartoonSubject) subject;
                String cartoonContent = cartoonSubject.getCartoonContent();
                logger.info("{}订阅的主题:{},{}了,内容为:{}", this.name, subjectName, action, cartoonContent);
                logger.info("{}要去看了~", this.name);
                logger.info("{}表示{}真好看", this.name, cartoonContent);
            }
        };
        Consumer<Subject> storyConsumer = (subject) -> {
            if (subject instanceof StorySubject) {
                String subjectName = subject.getName();
                String action = subject.getAction();
                StorySubject storySubject = (StorySubject) subject;
                List<String> list = storySubject.getStoryList();
                String content = StringUtils.join(list, ",");
                logger.info("{}订阅的主题:{},{}了,内容为:{}", this.name, subjectName, action, content);
                logger.info("{}要去看了~", this.name);
                logger.info("{}表示{}真好看", this.name, content);
            }
        };

        consumers.add(cartoonConsumer);
        consumers.add(storyConsumer);
    }

    @Override
    public void update(Subject subject) {
        //方式1:使用代理
        for (Observer observer : delegate) {
            observer.update(subject);
        }

        //方式2:使用消费者
        for (Consumer<Subject> consumer : consumers) {
            consumer.accept(subject);
        }

        //方式3:一个一个if (subject instanceof StorySubject) 判断
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
