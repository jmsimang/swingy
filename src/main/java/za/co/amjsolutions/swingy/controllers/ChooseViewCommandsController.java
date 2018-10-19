package za.co.amjsolutions.swingy.controllers;

import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.exceptions.ConstraintViolationException;
import za.co.amjsolutions.swingy.interfaces.ChooseViewCommands;
import za.co.amjsolutions.swingy.models.Hero;
import za.co.amjsolutions.swingy.models.map.Map;

import java.util.ArrayList;

public class ChooseViewCommandsController {
    private ChooseViewCommands chooseViewCommands;
    private Map map;

    public ChooseViewCommandsController(ChooseViewCommands chooseViewCommands) {
        this.chooseViewCommands = chooseViewCommands;
        map = Map.getInstance();
    }

    public String[] getAllHeroesFromDB() {
        ArrayList<String> heroes = DatabaseHandler.selectAllHeroes();
        String[] heroArray = new String[heroes.size()];
        heroArray = heroes.toArray(heroArray);
        return heroArray;
    }

    public void onChooseHeroSelection(int index) {
        Hero hero = DatabaseHandler.selectHeroById(index + 1);
        chooseViewCommands.updateViewScreen(hero.toString());
    }

    public void onChooseHeroView(int index) {
        Hero hero;
        try {
            hero = DatabaseHandler.selectHeroById(index + 1);
//            hero = DatabaseHandler.selectHeroById(index);
            hero.runValidation();
        } catch(ConstraintViolationException e) {
            chooseViewCommands.displayOutput(e.getMessage());
            return;
        }
        map.initialize(hero);
        chooseViewCommands.startGameView();
    }

    public void onCreateHeroView() {
        chooseViewCommands.createHeroView();
    }

    public void onSwitchView() {
        chooseViewCommands.switchView();
    }
}
