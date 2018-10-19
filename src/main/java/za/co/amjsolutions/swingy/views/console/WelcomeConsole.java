package za.co.amjsolutions.swingy.views.console;

import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.WelcomeViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.WelcomeViewCommands;
import za.co.amjsolutions.swingy.views.gui.WelcomeFrame;

import java.util.Scanner;

public class WelcomeConsole implements WelcomeViewCommands {
    private WelcomeViewCommandsController welcomeViewCommandsController;
    private Scanner scanner;
    private StringBuilder sb;

    @Override
    public void initScreen() {
        welcomeViewCommandsController = new WelcomeViewCommandsController(this);
        sb = new StringBuilder();
        sb.append("----------------------- SWINGY RPG -----------------------\n\n")
        .append("\t\tAn RPG fantasy game\n")
        .append("You have to fight your way against many Villains in order to\n")
        .append("to level up and gain experience points!\n\n")
        .append("A player can have multiple heroes of different types.\n")
        .append("To start the game, you have the following options:-\n")
        .append(" Options: [ Create | Choose | SWITCH ]\n")
        .append("\tCreate - to create a new Hero\n")
        .append("\tChoose - to choose a previously created Hero\n")
        .append("\tSWITCH : to play the game in GUI view..\n")
        .append("------------------------------------------------------------\n");
        System.out.println(sb.toString());
        scanner = Runner.getScanner();
        while (scanner.hasNext()) {
            String option = scanner.next();
            if (option.equalsIgnoreCase("Create")) {
                welcomeViewCommandsController.onCreateHero();
                break;
            } else if (option.equalsIgnoreCase("Choose")) {
                welcomeViewCommandsController.onChooseHero();
                break;
            } else if (option.equalsIgnoreCase("SWITCH")) {
                welcomeViewCommandsController.onSwitchViews();
                break;
            } else
                System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
        }
    }

    @Override
    public void chooseHeroView() {
        new ChooseConsole().initScreen();
    }

    @Override
    public void createHeroView() {
        new CreateConsole().initScreen();
    }

    @Override
    public void switchView() {
        System.out.println("Switching to GUI view...");
        new WelcomeFrame().initScreen();
    }
}
