package com.oneisall.learn.universal.design.pattern.strategy;

import com.oneisall.learn.universal.design.pattern.strategy.common.Predicate;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero;
import com.oneisall.learn.universal.design.pattern.strategy.domain.Hero.HeroType;
import com.oneisall.learn.universal.design.pattern.strategy.service.impl.HeroServiceImpl;
import com.oneisall.learn.universal.design.pattern.strategy.service.impl.StaturePredicate;
import com.oneisall.learn.universal.design.pattern.strategy.service.impl.TSPredicate;
import com.oneisall.learn.universal.design.pattern.strategy.service.impl.TypePredicate;

import java.util.ArrayList;
import java.util.List;


/**
 * @version 1.0
 */
@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {
        Hero hero1 = new Hero(HeroType.assassin, 165, Hero.HeroType.assassin.text + "65");
        Hero hero2 = new Hero(HeroType.saber, 166, HeroType.saber.text + "66");
        Hero hero3 = new Hero(HeroType.shooter, 167, HeroType.shooter.text + "67");

        Hero hero4 = new Hero(HeroType.assassin, 168, HeroType.assassin.text + "68");
        Hero hero5 = new Hero(HeroType.saber, 169, HeroType.saber.text + "69");
        Hero hero6 = new Hero(HeroType.shooter, 170, HeroType.shooter.text + "70");

        Hero hero7 = new Hero(HeroType.assassin, 171, HeroType.assassin.text + "71");
        Hero hero8 = new Hero(HeroType.saber, 172, HeroType.saber.text + "72");
        Hero hero9 = new Hero(HeroType.shooter, 173, HeroType.shooter.text + "73");

        Hero hero10 = new Hero(HeroType.assassin, 174, HeroType.assassin.text + "74");

        ArrayList<Hero> heroList = new ArrayList<>();
        heroList.add(hero1);
        heroList.add(hero2);
        heroList.add(hero3);
        heroList.add(hero4);
        heroList.add(hero5);
        heroList.add(hero6);
        heroList.add(hero7);
        heroList.add(hero8);
        heroList.add(hero9);
        heroList.add(hero10);

        HeroServiceImpl heroService = new HeroServiceImpl();
        List<Hero> typeHeroList = heroService.findHero(heroList, new TypePredicate());
        List<Hero> statureHeroList = heroService.findHero(heroList, new StaturePredicate());
        List<Hero> tsHeroList = heroService.findHero(heroList, new TSPredicate());

        System.out.println(typeHeroList);
        System.out.println(statureHeroList);
        System.out.println(tsHeroList);

        List<Hero> customHeroList = heroService.findHero(heroList, new Predicate<Hero>() {

            @Override
            public boolean test(Hero t) {
                return Hero.HeroType.assassin.equals(t.getType()) && t.getStature() > 170 && "刺客70".equals(t.getName());
            }
        });
        System.out.println(customHeroList);

        List<Hero> lambdaHeroList = heroService.findHero(heroList, t-> Hero.HeroType.assassin.equals(t.getType()) && t.getStature() > 170 && "刺客71".equals(t.getName()));
        System.out.println(lambdaHeroList);
    }

}
