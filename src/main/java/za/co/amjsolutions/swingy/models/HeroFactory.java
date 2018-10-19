package za.co.amjsolutions.swingy.models;

import za.co.amjsolutions.swingy.exceptions.HeroNotFoundException;

import static za.co.amjsolutions.swingy.models.Director.*;

public class HeroFactory {
    public static Hero newHero(String name, String heroClass) throws HeroNotFoundException {
        if (heroClass.equalsIgnoreCase("Knight")) return createKnight(name);
        else if (heroClass.equalsIgnoreCase("Warrior")) return createWarrior(name);
        else if (heroClass.equalsIgnoreCase("Swashbuckler")) return createSwashbuckler(name);
        else if (heroClass.equalsIgnoreCase("Assassin")) return createAssassin(name);
        else if (heroClass.equalsIgnoreCase("Magician")) return createMagician(name);
        throw new HeroNotFoundException(String.format("Hero Class: %s Does Not Exist!!!", heroClass));
    }
}
