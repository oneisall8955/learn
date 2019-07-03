package com.oneisall.learn.universal.design.pattern.strategy.example01.service.impl;

import com.oneisall.learn.universal.design.pattern.strategy.example01.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.example01.domain.Hero;
import com.oneisall.learn.universal.design.pattern.strategy.example01.service.HeroService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 英雄业务实现
 *
 * @author oneisall
 */
public class HeroServiceImpl implements HeroService {

    @Override
    public List<Hero> findHero(List<Hero> heroList, Predicate<Hero> predicate) {
        if (heroList == null || heroList.size() == 0) {
            return new ArrayList<>();
        }
        return heroList.stream().filter(predicate::test).collect(Collectors.toList());
    }

}
