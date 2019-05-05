package com.oneisall.learn.universal.design.pattern.strategy.service;

import com.oneisall.learn.universal.design.pattern.strategy.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero;

import java.util.List;


public interface HeroService {
    List<Hero> findHero(List<Hero> heroList, Predicate<Hero> prdicate);
}
