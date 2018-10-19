package za.co.amjsolutions.swingy.controllers;

import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.exceptions.ConstraintViolationException;
import za.co.amjsolutions.swingy.exceptions.HeroNotFoundException;
import za.co.amjsolutions.swingy.interfaces.CreateViewCommands;
import za.co.amjsolutions.swingy.models.Hero;
import za.co.amjsolutions.swingy.models.HeroFactory;
import za.co.amjsolutions.swingy.models.map.Map;

public class CreateViewCommandsController {
    private CreateViewCommands createViewCommands;
    private Map map;

    public CreateViewCommandsController(CreateViewCommands createViewCommands) {
        this.createViewCommands = createViewCommands;
        map = Map.getInstance();
    }

    public void onCreateHero(String name, String heroClass) {
        Hero hero = null;
        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.runValidation();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        } catch (HeroNotFoundException e) {
            createViewCommands.displayOutput(e.getMessage());
            createViewCommands.inputScreen();
            return;
        }
        int heroId = DatabaseHandler.createAHero(hero.getName(), hero.getHeroClass(), hero.getAttack(),
                hero.getDefense(), hero.getHp(), hero.getLevel(), hero.getXp());
        hero.setHeroID(heroId);
        map.initialize(hero);
        createViewCommands.startGameView();
    }

    public void onGoBackToWelcome() {
        createViewCommands.welcomeScreen();
    }

    public void onSwitchView() {
        createViewCommands.switchView();
    }
}
