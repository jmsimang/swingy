package za.co.amjsolutions.swingy.models;

import za.co.amjsolutions.swingy.models.artefacts.Armor;
import za.co.amjsolutions.swingy.models.artefacts.Helm;
import za.co.amjsolutions.swingy.models.artefacts.Weapon;

public class HeroBuilder {
    private int hero_id;
    private String name;
    private int attack;
    private int defense;
    private int hp;
    private String heroClass;
    private int level;
    private int xp;
    private Armor armor;
    private Helm helm;
    private Weapon weapon;

    public HeroBuilder setHeroID(int hero_id) {
        this.hero_id = hero_id;
        return this;
    }

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public HeroBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public HeroBuilder setHitPoints(int hp) {
        this.hp = hp;
        return this;
    }

    public HeroBuilder setHeroClass(String heroClass) {
        this.heroClass = heroClass;
        return this;
    }

    public HeroBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public HeroBuilder setXP(int xp) {
        this.xp = xp;
        return this;
    }

    public HeroBuilder setArmor(Armor armor) {
        this.armor = armor;
        return this;
    }

    public HeroBuilder setHelm(Helm helm) {
        this.helm = helm;
        return this;
    }

    public HeroBuilder setWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public Hero getHero() {
        return new Hero(hero_id, name, attack, defense, hp, heroClass, level, xp, armor, helm, weapon);
    }
}
