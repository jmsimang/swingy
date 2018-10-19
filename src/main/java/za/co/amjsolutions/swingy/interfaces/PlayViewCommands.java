package za.co.amjsolutions.swingy.interfaces;

import za.co.amjsolutions.swingy.models.map.Grid;
import za.co.amjsolutions.swingy.models.map.Map;

public interface PlayViewCommands {
    void initScreen();
    void showGrid(boolean[][] points, Grid grid);
    void updateViewScreen(Map map);
    void displayOutput(String message);
    void gameOver();
    void getCollisionInput();
    boolean changeArtifact(String message);
    void switchView();
}
