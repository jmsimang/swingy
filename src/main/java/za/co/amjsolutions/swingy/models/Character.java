package za.co.amjsolutions.swingy.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
    @NotNull(message = "Name field cannot be empty")
    @Length(min = 1, max = 16, message = "Name length should be > 0 and < 17")
    protected String name;

    @Min(value = 0, message = "Attack points cannot be less than 0")
    protected int attack;

    @Min(value = 0, message = "Defense points cannot be less than 0")
    protected int defense;

    @Min(value = 1, message = "Hit points cannot be less than 1")
    protected int hp;

    public Character(String name, int attack, int defense, int hp) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getHp() {
        return hp;
    }

    public boolean canFight(Character enemy) {
        while (enemy.getHp() > 0 && this.getHp() > 0) {
            enemy.fight(this);
            if (this.getHp() > 0)
                this.fight(enemy);
        }
        return (this.getHp() > 0);
    }

    public void fight(Character enemy) {
        if (this.getAttack() > enemy.getDefense()) {
            enemy.setHp((enemy.getHp() + enemy.getDefense()) - this.getAttack());
        } else if (ThreadLocalRandom.current().nextInt(0, 10) <= 2) {
            enemy.setHp((enemy.getHp() + enemy.getDefense()) - this.getAttack());
        } else if (enemy.getAttack() > this.getDefense()) {
            this.setHp((this.getHp() + this.getDefense()) - enemy.getAttack());
        }
    }
}
