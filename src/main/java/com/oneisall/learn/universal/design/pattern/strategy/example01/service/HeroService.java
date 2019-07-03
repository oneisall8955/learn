package com.oneisall.learn.universal.design.pattern.strategy.example01.service;

import com.oneisall.learn.universal.design.pattern.strategy.example01.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.example01.domain.Hero;

import java.util.List;


/**
 * 英雄业务接口
 *
 * @author oneisall
 */
public interface HeroService {
    /**
     * 查找符合条件的英雄
     *
     * @param heroList  英雄列表
     * @param predicate 查找符合的断言
     * @return 符合断言的英雄列表
     */
    List<Hero> findHero(List<Hero> heroList, Predicate<Hero> predicate);
}
