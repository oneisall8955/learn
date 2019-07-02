package com.oneisall.learn.universal.design.pattern.observer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 观察者
 *
 * @author : oneisall
 * @version : v1 2019/7/2 11:51
 */
@SuppressWarnings("all")
public class StoryObserver implements Observer {

    private String name;

    public StoryObserver(String name) {
        this.name = name;
    }

    private static Logger logger = LoggerFactory.getLogger(StoryObserver.class);

    @Override
    public void update(Subject subject) {
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
    }
}
