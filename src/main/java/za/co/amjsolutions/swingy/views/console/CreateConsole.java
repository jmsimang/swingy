package za.co.amjsolutions.swingy.views.console;

import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.CreateViewCommandsController;
import za.co.amjsolutions.swingy.exceptions.HeroClassNotFoundException;
import za.co.amjsolutions.swingy.interfaces.CreateViewCommands;
import za.co.amjsolutions.swingy.views.gui.CreateFrame;

import java.util.Scanner;

public class CreateConsole implements CreateViewCommands {
    private CreateViewCommandsController createViewCommandsController;
    private String name;
    private String heroClass;
    private String option;
    private Scanner scanner;
    private StringBuilder sb;

    @Override
    public void initScreen() {
        createViewCommandsController = new CreateViewCommandsController(this);
        inputScreen();
    }

    @Override
    public void inputScreen() {
        try {
            scanner = Runner.getScanner();
            sb = new StringBuilder();
            sb.append("----------------------- SWINGY RPG -----------------------\n");
            sb.append("---------------------- CREATE HERO -----------------------\n");
            sb.append("To create a Hero, you need two things:\n\t1. Hero name, \n\t2. Hero class'\n\n")
            .append("1. Enter the name for your Hero: ");
            System.out.print(sb.toString());
            name = scanner.next();
            if (name.length() == 0)
                throw new HeroClassNotFoundException("ERROR: Hero name cannot be empty!");

            sb.setLength(0);
            sb.append("\n2. Hero Class list:\n")
            .append("\tHero Class\tAttack\tDefense\tHit Points (HP)\n")
            .append("\t- Knight\t50\t\t100\t\t30 \n\t- Warrior\t55\t80\t20\n")
            .append("\t- Swashbuckler\t\t20\t\t30\t50 \n\t- Assassin\t75\t60\t100\n")
            .append("\t- Magician\t60\t\t60\t\t40\n\n")
            .append("2. Enter your Hero class from the list above: ");
            System.out.println(sb.toString());
            heroClass = scanner.next();
            if (heroClass.length() == 0)
                throw new HeroClassNotFoundException("ERROR: Hero class cannot be empty!");

            System.out.println(String.format("Your Hero is \n[name: %s]\n[class: %s]\n", name, heroClass));
            System.out.print("Enter 'Create' to accept and create your Hero:");
            while (scanner.hasNext()) {
                option = scanner.next();
                if (option.equalsIgnoreCase("Create")) {
                    createViewCommandsController.onCreateHero(name, heroClass);
                    break;
                } else
                    System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (HeroClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void displayOutput(String message) {
        System.out.println(String.format("ERROR: %s", message));
    }

    @Override
    public void startGameView() {
        new PlayConsole().initScreen();
    }

    @Override
    public void welcomeScreen() {
        new WelcomeConsole().initScreen();
    }

    @Override
    public void switchView() {
        System.out.println("Switching to GUI view...");
        new CreateFrame().initScreen();
    }
}
