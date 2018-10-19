package za.co.amjsolutions.swingy.views.console;

import za.co.amjsolutions.swingy.Runner;
<<<<<<< HEAD
import za.co.amjsolutions.swingy.controllers.PlayViewCommandsController;
import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.interfaces.PlayViewCommands;
import za.co.amjsolutions.swingy.models.map.Grid;
import za.co.amjsolutions.swingy.models.map.Map;
import za.co.amjsolutions.swingy.views.gui.PlayFrame;

import java.util.Scanner;

public class PlayConsole implements PlayViewCommands {
    private PlayViewCommandsController playViewCommandsController;
    private static Scanner scanner;

    @Override
    public void initScreen() {
//        DatabaseHandler.connectToDB();
        playViewCommandsController = new PlayViewCommandsController(this);
        playViewCommandsController.onInitScreen();
    }

    public void getUserInput() {
        scanner = Runner.getScanner();
        System.out.println("Available commands:\n\tMovement: [ NORTH, SOUTH, EAST, WEST ]\n\tGUI View: [ SWITCH ]");
        while (scanner.hasNext()) {
            String option = scanner.next().toUpperCase();
            if (option.equals("NORTH") || option.equals("SOUTH") || option.equals("EAST") || option.equals("WEST")) {
                playViewCommandsController.onMove(option);
                break;
            } else if (option.equals("GRID")) {
                playViewCommandsController.onShowGrid();
                break;
            } else if (option.equals("SWITCH")) {
                playViewCommandsController.onSwitchView();
                break;
            } else {
                System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
            }
        }
    }

    @Override
    public void showGrid(boolean[][] points, Grid grid) {
        System.out.println(String.format("GRID %dx%d\n", points.length, points.length));
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (grid.getX() == j && grid.getY() == i)
                    System.out.print("H ");
                else if (points[i][j])
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    @Override
    public void updateViewScreen(Map map) {
        System.out.println("\n-------------------- HERO INFORMATION --------------------");
        System.out.println(map.getHero().toString());
        System.out.println("---------------- GRID ------------------");
        System.out.println(String.format("Hero: Coordinates (X: %d, Y: %d)\n", map.getGrid().getX(), map.getGrid().getY()));
        showGrid(map.getPoints(), map.getGrid());
        System.out.println("----------------------------------------\n");
        getUserInput();
    }

    @Override
    public void displayOutput(String message) {
        System.out.println(message);
    }

    @Override
    public void gameOver() {
        System.out.println("----------- GAME INFORMATION -----------");
        System.out.println("Game over!");
        System.out.println("----------------------------------------\n");
        Runner.getWindow().dispose();
        DatabaseHandler.closeDBConnection();
        System.exit(1);
    }

    @Override
    public void getCollisionInput() {
        scanner = Runner.getScanner();
        System.out.println("You encountered a Villain! FIGHT or RUN?");
        System.out.println("\tFIGHT\t- Fight with the villain.\n\tRUN\t- Run from villain (50% chance of moving to previous position)");
        while (scanner.hasNext()) {
            String option = scanner.next();
            if (option.equalsIgnoreCase("Fight")) {
                playViewCommandsController.onFight();
                break;
            } else if (option.equalsIgnoreCase("Run")) {
                playViewCommandsController.onRun();
                break;
            } else {
                System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
            }
        }
    }

    @Override
    public boolean changeArtifact(String message) {
        scanner = Runner.getScanner();
        System.out.println("Would you like to replace the "+message+" artifact with a new one?");
        System.out.println("Yes or No?");
        while (scanner.hasNext()) {
            String option = scanner.next();
            if (option.equalsIgnoreCase("Yes")) {
                return true;
            } else if (option.equalsIgnoreCase("No")) {
                return false;
            } else {
                System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
            }
        }
        return false;
    }

    @Override
    public void switchView() {
        DatabaseHandler.closeDBConnection();
        new PlayFrame().initScreen();
=======
import za.co.amjsolutions.swingy.exceptions.CommandNotFoundException;
import za.co.amjsolutions.swingy.exceptions.InvalidHeroProvidedException;
import za.co.amjsolutions.swingy.models.Hero;

import java.util.Scanner;

public class PlayConsole {
    private static Scanner scanner;
    private static Hero hero;

    public void StartPlayConsole(Hero hero) {
        try {
            this.hero = hero;
            if (hero == null) {
                throw new InvalidHeroProvidedException("ERROR: Invalid Hero provided!");
            }
            System.out.println(String.format("You have chosen the following Hero: \n%s", this.hero.toString()));
            System.out.println("The rules of the game are as follows...");
            System.out.println("Hero position: Coordinates ( x , x )\nDo you choose to FIGHT or RUN?");
            scanner = Runner.getScanner();

            while (scanner.hasNext()) {
                String opt = scanner.nextLine();
                if (opt.equalsIgnoreCase("FIGHT")) {
                     this.EngageEnemy();
                } else if (opt.equalsIgnoreCase("RUN")) {
                     this.FleeEnemy();
                } else {
                    throw new CommandNotFoundException("ERROR: You entered an invalid command. Try again!");
                }
            }
        } catch (InvalidHeroProvidedException e) {
            System.err.println(e.getMessage());
        } catch (CommandNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private void EngageEnemy() {

    }

    private void FleeEnemy() {

>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
    }
}
