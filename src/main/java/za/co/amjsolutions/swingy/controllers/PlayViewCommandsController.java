package za.co.amjsolutions.swingy.controllers;

import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.interfaces.PlayViewCommands;
import za.co.amjsolutions.swingy.models.Hero;
import za.co.amjsolutions.swingy.models.Villain;
import za.co.amjsolutions.swingy.models.artefacts.Armor;
import za.co.amjsolutions.swingy.models.artefacts.Artifact;
import za.co.amjsolutions.swingy.models.artefacts.Helm;
import za.co.amjsolutions.swingy.models.artefacts.Weapon;
import za.co.amjsolutions.swingy.models.map.Grid;
import za.co.amjsolutions.swingy.models.map.Map;

import java.util.Random;

public class PlayViewCommandsController {
    private PlayViewCommands playViewCommands;
    private Map map;
    private Grid grid;

    public PlayViewCommandsController(PlayViewCommands playViewCommands) {
        this.playViewCommands = playViewCommands;
        map = Map.getInstance();
        grid = new Grid(0, 0);
    }

    public void onInitScreen() {
//        DatabaseHandler.closeDBConnection();
//        DatabaseHandler.connectToDB();
        playViewCommands.updateViewScreen(map);
    }

    public void onSwitchView() {
        playViewCommands.switchView();
    }

    public void onShowGrid() {
        playViewCommands.showGrid(map.getPoints(), map.getGrid());
        playViewCommands.updateViewScreen(map);
    }

    public void onMove(String direction) {
        int x = map.getGrid().getX();
        int y = map.getGrid().getY();
        grid.setX(x);
        grid.setY(y);
        if (direction.equalsIgnoreCase("NORTH")) y--;
        else if (direction.equalsIgnoreCase("SOUTH")) y++;
        else if (direction.equalsIgnoreCase("EAST")) x++;
        else if (direction.equalsIgnoreCase("WEST")) x--;
        if (x <= 0 || y <= 0 || x >= map.getSize() || y >= map.getSize()) {
            gameEnd();
            return;
        }
        map.getGrid().setX(x);
        map.getGrid().setY(y);
        if (map.getPoints()[y][x]) collision();
        if (map.getHero().getHp() > 0) playViewCommands.updateViewScreen(map);
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            playViewCommands.displayOutput("You were able to move to your previous position!");
            map.getGrid().setY(grid.getY());
            map.getGrid().setX(grid.getX());
        } else {
            playViewCommands.displayOutput("You have to fight!");
            onFight();
        }
    }

    public void onFight() {
        Villain villain = map.createVillain();
        int xp = map.fightWithVillain(villain);

        if (xp >= 0) {
            playViewCommands.displayOutput("FIGHT RESULT: You win! "+xp+" XP earned!");
            addXpPoints(xp);
            map.getPoints()[map.getGrid().getY()][map.getGrid().getX()] = false;
            setArtifact(villain.getArtifact());
        } else {
            playViewCommands.displayOutput("FIGHT RESULT: You lost the fight!");
            playViewCommands.gameOver();
        }
    }

    private void setArtifact(Artifact artifact) {
        if (artifact != null) {
            if (artifact instanceof Armor) {
                if (playViewCommands.changeArtifact("Armor: "+map.getHero().getArmor()+", Found: "+artifact)) {
                    map.getHero().equipWithArmor((Armor) artifact);
                    playViewCommands.displayOutput("New armor equipped: " + artifact);
                }
            } else if (artifact instanceof Helm) {
                if (playViewCommands.changeArtifact("Helm: "+map.getHero().getHelm()+", Found: "+artifact)) {
                    map.getHero().equipWithHelm((Helm) artifact);
                    playViewCommands.displayOutput("New helm equipped: " + artifact);
                }
            } else if (artifact instanceof Weapon) {
                if (playViewCommands.changeArtifact("Weapon: "+map.getHero().getWeapon()+", Found: "+artifact)) {
                    map.getHero().equipWithWeapon((Weapon) artifact);
                    playViewCommands.displayOutput("New weapon equipped: " + artifact);
                }
            }
        }
    }

    private void collision() {
        playViewCommands.getCollisionInput();
    }

    private void gameEnd() {
        playViewCommands.displayOutput("You win! " + map.getSize() * 75 + " XP earned!");
//        map.getHero().increaseXP(points);
        addXpPoints(map.getSize() * 75);
        updateRecords();
        Map newMap = Map.getInstance();
        newMap.initialize(map.getHero());
//        updateMap(map);
//        playViewCommands.initScreen();
//        playViewCommands.gameOver();
//        playViewCommands.initScreen();
        playViewCommands.updateViewScreen(newMap);
    }

    private void addXpPoints(int points) {
        int level = map.getHero().getLevel();
        map.getHero().increaseXP(points);
        if (level != map.getHero().getLevel())
            System.out.println("Hero has leveled up! New level: "+map.getHero().getLevel());
    }

    private void updateRecords() {
        Hero hero = map.getHero();
        DatabaseHandler.updateHero(hero);
    }
}
