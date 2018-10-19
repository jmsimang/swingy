package za.co.amjsolutions.swingy.models;

import za.co.amjsolutions.swingy.exceptions.ConstraintViolationException;
import za.co.amjsolutions.swingy.models.artefacts.Armor;
import za.co.amjsolutions.swingy.models.artefacts.Helm;
import za.co.amjsolutions.swingy.models.artefacts.Weapon;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hero extends Character {
    @NotNull(message = "Hero class cannot be empty")
    @Size(min = 4, max = 16, message = "Hero class should contain 4 to 16 characters")
    private String heroClass;

    @Min(value = 1, message = "Hero level cannot be less than 1")
    private int level;

    @Min(value = 0, message = "Hero XP cannot be less than 0")
    private int xp;

    private int heroID;
    private Armor armor;
    private Helm helm;
    private Weapon weapon;

    public Hero(int heroID, String name, int attack, int defense, int hp, String heroClass, int level, int xp, Armor armor, Helm helm, Weapon weapon) {
        super(name, attack, defense, hp);
        this.heroID = heroID;
        this.heroClass = heroClass;
        this.level = level;
        this.xp = xp;
        this.armor = armor;
        this.helm = helm;
        this.weapon = weapon;
    }

    public void setHeroID(int heroID) { this.heroID = heroID; }
    public void setHeroClass(String heroClass) { this.heroClass = heroClass; }
    public void setLevel(int level) { this.level = level; }
    public void setXp(int xp) { this.xp = xp; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public void setHelm(Helm helm) { this.helm = helm; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public String getHeroClass() { return heroClass; }
    public int getLevel() {
        return level;
    }
    public int getXp() { return xp; }
    public Armor getArmor() { return armor; }
    public Helm getHelm() {
        return helm;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public int getHeroID() { return heroID; }


    public void equipWithArmor(Armor a) {
        if (armor != null)
            defense -= a.getPoints();
        armor = a;
        defense += armor.getPoints();
    }

    public void equipWithHelm(Helm h) {
        if (helm != null)
            hp -= helm.getPoints();
        helm = h;
        hp += h.getPoints();
    }

    public void equipWithWeapon(Weapon w) {
        if (weapon != null)
            attack -= weapon.getPoints();
        weapon = w;
        attack += w.getPoints();
    }

    public void levelUp() {
        level += 1;
        this.setHp(this.getLevel() * 10 + this.getHp() + 50);
        this.setAttack(this.getLevel() * 5 + this.getAttack());
        this.setDefense(this.getLevel() * 3 + this.getDefense());
    }

    public void increaseXP(int points) {
        int nextLevel = level * 1000 + ((level - 1) * (level - 1)) * 450;
        if (getXp() + points >= nextLevel)
            levelUp();
        xp += xp + points;
    }

    public void runValidation() throws ConstraintViolationException {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Hero validation errors found: %d\n", constraintViolations.size()));
            for (ConstraintViolation<Hero> c : constraintViolations) {
                sb.append(String.format("Property: [%s], ", c.getPropertyPath()))
                .append(String.format("Value: [%s], ", c.getInvalidValue()))
                .append(String.format("Message: [%s]\n", c.getMessage()));
            }
            throw new ConstraintViolationException(sb.toString());
        }
    }

    @Override
    public String toString() {
        String a = (armor == null) ? "None" : armor.toString();
        String h = (helm == null) ? "None" : helm.toString();
        String w = (weapon == null) ? "None" : weapon.toString();
        return String.format("Hero Details\nName: %s\nClass: %s\nAttack: %d\nDefense: %d\nHP: %d\n" +
            "Level: %d\nXP: %d\nArmor: %s\nHelm: %s\nWeapon: %s\n",
                name, heroClass, attack, defense, hp, level, xp, a, h, w);
    }
}
