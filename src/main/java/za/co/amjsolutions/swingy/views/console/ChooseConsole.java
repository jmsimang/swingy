package za.co.amjsolutions.swingy.views.console;

import za.co.amjsolutions.swingy.Runner;
<<<<<<< HEAD
import za.co.amjsolutions.swingy.controllers.ChooseViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.ChooseViewCommands;
import za.co.amjsolutions.swingy.views.gui.ChooseFrame;

=======
import za.co.amjsolutions.swingy.controllers.databaseConnection;
import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.exceptions.CommandNotFoundException;
import za.co.amjsolutions.swingy.models.Hero;

import java.util.ArrayList;
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
import java.util.Scanner;

public class ChooseConsole implements ChooseViewCommands {
    private ChooseViewCommandsController chooseViewCommandsController;
    private Scanner scanner;
    private int index = -1;

    public void initScreen() {
        chooseViewCommandsController = new ChooseViewCommandsController(this);
        showUserCommands();
    }

    private void showUserCommands() {
        System.out.println("----------------------- SWINGY RPG -----------------------\n");
        System.out.println("---------------------- CHOOSE HERO -----------------------\n");
        System.out.println("\tHero list (previously created):");

<<<<<<< HEAD
        String[] DbData = getAllDbData();
        int dataSize = DbData.length;
        if (dataSize == 0)
            System.out.println("\t-> No previously created Heroes found");
        else {
            System.out.println("No.\tHero name\tHero class");
            for (String hero: DbData)
                System.out.println(hero);
        }
        System.out.println("You have the following options: Enter ");
        if (dataSize != 0)
            System.out.println("\t{Hero number} - to view Hero details");
        System.out.println("\tCreate - to create a new Hero");
        System.out.println("\tSWITCH : to play the game in GUI view..\n");

        scanner = Runner.getScanner();
        while (scanner.hasNext()) {
            String option = scanner.next();
            if (isOptionADigit(option, dataSize)) {
                index = Integer.parseInt(option) - 1;
                chooseViewCommandsController.onChooseHeroSelection(index);
                System.out.println("\tEnter 'Choose' to select this Hero\n");
            } else if (option.equalsIgnoreCase("choose") && index != -1) {
                chooseViewCommandsController.onChooseHeroView(index);
                break;
            } else if (option.equalsIgnoreCase("create")) {
                chooseViewCommandsController.onCreateHeroView();
                break;
            } else if (option.equalsIgnoreCase("switch")) {
                chooseViewCommandsController.onSwitchView();
                break;
            } else {
                System.out.println(String.format("ERROR: %s is not a valid option. Try again!", option));
=======
            data = new databaseConnection().selectAllHeroes();
            System.out.println("Hero List: \n" +data);
            if (data == null) {
                System.out.println("There are currently no previously created Heroes saved.\n" +
                        "You can either enter, 1. To go back to create a new Hero.\n2. Exit program.");
                scanner = Runner.getScanner();
                while (scanner.hasNext()) {
                    int option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case 1:
                            new CreateConsole().createScreen();
                            break;
                        case 2:
                            System.out.println("Thank you for attempting to play :D. See you!");
                            System.exit(EXIT_FAILURE);
                            break;
                        default:
                            throw new CommandNotFoundException(String.format("ERROR: Option %d is not a valid option. Try again!", option));
//                            System.out.println(String.format("ERROR: Option %d is not a valid option. Try again!", option));
                    }
                }
            } else {
                sb.setLength(0);
                sb.append(data);
                System.out.println(sb.toString());
                System.out.println("Please enter a Hero number to select: ");
                scanner = Runner.getScanner();
                while (scanner.hasNext()) {
                    int selection = Integer.parseInt(scanner.nextLine());
                    switch (selection) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            break;
                    }
                }
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
            }
        }
    }

<<<<<<< HEAD
    private String[] getAllDbData() {
        return chooseViewCommandsController.getAllHeroesFromDB();
    }

    private boolean isOptionADigit(String input, int dbLength) {
        try {
            int digit = Integer.parseInt(input);
            if (digit <= 0 || digit > dbLength)
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void updateViewScreen(String info) {
        System.out.println(info);
    }

    @Override
    public void startGameView() {
        new PlayConsole().initScreen();
    }

    @Override
    public void createHeroView() {
        new CreateConsole().initScreen();
    }

    @Override
    public void displayOutput(String message) {
        System.out.println("ERROR: " + message);
        showUserCommands();
    }

    @Override
    public void switchView() {
        System.out.println("Switching to GUI view...");
        new ChooseFrame().initScreen();
=======
    private String readHeroData() {
        ArrayList<Hero> data = DatabaseHandler.retrieveAllHeroes();

//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("heroes.txt"));
//            String line;
//            StringBuilder sBuilder = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//                sBuilder.append("\tHero Name\tHero Class\tAttack\tDefense\tHit Points (HP)\n")
//                .append(String.format("\t%s\t%s\t%s\t%s\t%s\n",
//                        line.split(",")[0],
//                        line.split(",")[1],
//                        line.split(",")[2],
//                        line.split(",")[3],
//                        line.split(",")[4]));
//            }
//            data = sBuilder.toString();
//        } catch (FileNotFoundException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (NumberFormatException e) {
//            System.err.println(e.getMessage());
//        }
//        return data;
        return data.toString();
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
    }
}
