package za.co.amjsolutions.swingy.interfaces;

public interface ChooseViewCommands {
    void initScreen();
    void updateViewScreen(String info);
    void startGameView();
    void createHeroView();
    void displayOutput(String message);
    void switchView();
}
