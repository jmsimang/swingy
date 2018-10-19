package za.co.amjsolutions.swingy.models;

import za.co.amjsolutions.swingy.models.artefacts.Helm;
import za.co.amjsolutions.swingy.models.artefacts.Weapon;

import java.util.concurrent.ThreadLocalRandom;

public class Director {
    private static int points = ThreadLocalRandom.current().nextInt(1, 50);
    private static HeroBuilder construct(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setName(name)
                        .setAttack(50)
                            .setDefense(50)
                                .setHitPoints(5);
        return heroBuilder;
    }

    public static Hero createKnight(String name) {
        HeroBuilder heroBuilder1 = construct(name);
        heroBuilder1.setAttack(50)
                        .setDefense(100)
                            .setHitPoints(30)
                                .setHeroClass("KNIGHT")
                                    .setLevel(1)
                                        .setXP(0)
                                            .setWeapon(new Weapon("Fire Flaming Sword", points));
        return heroBuilder1.getHero();
    }

    public static Hero createWarrior(String name) {
        HeroBuilder heroBuilder1 = construct(name);
        heroBuilder1.setAttack(55)
                        .setDefense(80)
                            .setHitPoints(20)
                                .setHeroClass("WARRIOR")
                                    .setLevel(1)
                                        .setXP(0);
        return heroBuilder1.getHero();
    }

    public static Hero createSwashbuckler(String name) {
        HeroBuilder heroBuilder1 = construct(name);
        heroBuilder1.setAttack(20)
                        .setDefense(30)
                            .setHitPoints(50)
                                .setHeroClass("Swashbuckler")
                                    .setLevel(1)
                                       .setXP(0);
        return heroBuilder1.getHero();
    }

    public static Hero createAssassin(String name) {
        HeroBuilder heroBuilder1 = construct(name);
        heroBuilder1.setAttack(75)
                        .setDefense(60)
                            .setHitPoints(100)
                                .setHeroClass("Assassin")
                                    .setLevel(1)
                                        .setXP(0)
                                            .setWeapon(new Weapon("Bow & Arrow", points));
        return heroBuilder1.getHero();
    }

    public static Hero createMagician(String name) {
        HeroBuilder heroBuilder1 = construct(name);
        heroBuilder1.setAttack(60)
                        .setDefense(60)
                            .setHitPoints(40)
                                .setHeroClass("Magician")
                                    .setLevel(1)
                                        .setXP(0).setHelm(new Helm("Magic Orb", points));
        return heroBuilder1.getHero();
    }
}
