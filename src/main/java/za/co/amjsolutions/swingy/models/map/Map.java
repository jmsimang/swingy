package za.co.amjsolutions.swingy.models.map;

import za.co.amjsolutions.swingy.models.*;
import za.co.amjsolutions.swingy.models.Character;
import za.co.amjsolutions.swingy.models.artefacts.*;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private int size;
    private boolean[][] points;
    private Hero hero;
    private Grid grid;
    private static Map map = null;

    public static Map getInstance() {
        if (map == null)
            map = new Map();
        return map;
    }

    public void setSize(int size) { this.size = size; }
    public void setPoints(boolean[][] points) { this.points = points; }
    public void setHero(Hero hero) { this.hero = hero; }

    public void initialize(Hero hero) {
        this.hero = hero;
        buildMap();
        placeVillainsOnMap();
        placeHeroOnMap();
    }

    private void buildMap() {
        setSize((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        if (getSize() > 23)
            setSize(23);
        points = new boolean[size][size];
    }

    private void placeHeroOnMap() {
        grid = new Grid(size / 2, size / 2);
        points[grid.getY()][grid.getX()] = false;
    }

    private void placeVillainsOnMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (((hero.getLevel() + 1) * 10) >= ThreadLocalRandom.current().nextInt(0, 101))
                    points[i][j] = true;
            }
        }
    }

    public Villain createVillain() {
        Artifact artifact = createArtifact();
        int attack = ThreadLocalRandom.current().nextInt(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defense = ThreadLocalRandom.current().nextInt(hero.getDefense() - 20, hero.getDefense() + 2 + hero.getLevel());
        int hp = ThreadLocalRandom.current().nextInt(hero.getHp() - 50, hero.getHp() + 20 + hero.getLevel());
        int villainClass = ThreadLocalRandom.current().nextInt(0, 2);
        String villainType = villainClass == 0 ? "Sorcerer" : villainClass == 1 ? "Mad King" : "King slayer";
        return new Villain(villainType, attack, defense, hp, artifact);
    }

    private Artifact createArtifact() {
        Artifact artifact = null;
        int artifactType = ThreadLocalRandom.current().nextInt(0, 15);
        if (artifactType == 0)
            artifact = new Armor("Shield", ThreadLocalRandom.current().nextInt(1, 14 * (hero.getLevel() + 1)));
        else if (artifactType == 1)
            artifact = new Weapon("Sword", ThreadLocalRandom.current().nextInt(1, 15 * (hero.getLevel() + 1)));
        else if (artifactType == 2)
            artifact = new Helm("Helmet", ThreadLocalRandom.current().nextInt(1, 13 * (hero.getLevel() + 1)));
        else if (artifactType == 3)
            artifact = new Armor("Magic Orb", ThreadLocalRandom.current().nextInt(1, 13 * (hero.getLevel() + 3)));
        else if (artifactType == 5)
            artifact = new Weapon("Hand Cannon", ThreadLocalRandom.current().nextInt(1, 7 * (hero.getLevel() + 3)));
        else if (artifactType == 4)
            artifact = new Helm("Invincible Cloak", ThreadLocalRandom.current().nextInt(1, 7 * (hero.getLevel() + 3)));
        return artifact;
    }

    public int fightWithVillain(Character enemy) {
        int rand = ThreadLocalRandom.current().nextInt(21, 121);
        int xp = enemy.getAttack() + enemy.getDefense();// + enemy.getHp();

        if (rand < 25)
            return xp;
        else if (rand > 108)
            return -1;
        return hero.canFight(enemy) ? xp : -1;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getPoints() {
        return points;
    }

    public Hero getHero() {
        return hero;
    }

    public Grid getGrid() {
        return grid;
    }
}
