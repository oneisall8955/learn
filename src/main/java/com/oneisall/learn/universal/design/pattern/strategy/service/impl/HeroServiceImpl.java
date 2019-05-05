package com.oneisall.learn.universal.design.pattern.strategy.service.impl;

import com.oneisall.learn.universal.design.pattern.strategy.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero;
import com.oneisall.learn.universal.design.pattern.strategy.service.HeroService;

import java.util.ArrayList;
import java.util.List;



public class HeroServiceImpl implements HeroService {

    @Override
    public List<Hero> findHero(List<Hero> heroList, Predicate<Hero> predicate) {
        List<Hero> result = new ArrayList<Hero>();
        if (heroList == null || heroList.size() == 0) {
            return result;
        }
        heroList.forEach(hero -> {
            if (predicate.test(hero)) {
                result.add(hero);
            }
        });

        return result;
    }

}
