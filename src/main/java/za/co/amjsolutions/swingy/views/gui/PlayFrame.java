package za.co.amjsolutions.swingy.views.gui;

import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.PlayViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.PlayViewCommands;
import za.co.amjsolutions.swingy.models.map.Grid;
import za.co.amjsolutions.swingy.models.map.Map;
import za.co.amjsolutions.swingy.views.console.PlayConsole;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayFrame extends JFrame implements PlayViewCommands {
    private JPanel playFrame;
    private JEditorPane gridPane;
    private JEditorPane infoPane;
    private JButton confirmMoveBtn;
    private JComboBox movementCombo;
    private JButton switchViewBtn;
    private JLabel directionLbl;
    private PlayViewCommandsController playViewCommandsController;

    @Override
    public void initScreen() {
//        DatabaseHandler.connectToDB();
        playViewCommandsController = new PlayViewCommandsController(this);
        createUIComponents();
        initListeners();
        playViewCommandsController.onInitScreen();
    }

    @Override
    public void showGrid(boolean[][] points, Grid grid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("GRID %dx%d\n", points.length, points.length));
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (grid.getX() == j && grid.getY() == i)
                    stringBuilder.append("X ");
                else if (points[i][j])
                    stringBuilder.append("0 ");
                else
                    stringBuilder.append(". ");
            }
            stringBuilder.append("\n");
        }
        gridPane.setText(stringBuilder.toString());
    }

    @Override
    public void updateViewScreen(Map map) {
        infoPane.setText(String.format("Hero details: %s\nHero Position:\nCoordinates (X: %d, Y: %d)\n",
                map.getHero().toString(), map.getGrid().getX(), map.getGrid().getY()));
        showGrid(map.getPoints(), map.getGrid());
    }

    @Override
    public void displayOutput(String message) {
        JOptionPane.showMessageDialog(Runner.getWindow(), message);
    }

    @Override
    public void gameOver() {
        Runner.closeWindow();
        Runner.getWindow().dispose();
        Runner.closeDBConnection();
    }

    @Override
    public void getCollisionInput() {
        Object commands[] = {"Fight", "Run"};
        int option = JOptionPane.showOptionDialog(Runner.getWindow(), "Position occupied by Villain!", "Fight or Run?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, commands, commands[0]);
        if ((option == JOptionPane.NO_OPTION)) playViewCommandsController.onRun();
        else playViewCommandsController.onFight();
    }

    @Override
    public boolean changeArtifact(String message) {
        Object commands[] = {"Replace", "Leave"};
        int option = JOptionPane.showOptionDialog(Runner.getWindow(), "Would you like to replace the " + message + " artifact with a new one?",
                "Replace artifact?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, commands, commands[0]);
        return ((option == JOptionPane.YES_OPTION));
    }

    private void createUIComponents() {
        Runner.getWindow().setTitle("Gameplay");
        this.setVisible(true);
        Runner.getWindow().setContentPane(playFrame);
        Runner.getWindow().revalidate();
        Runner.showWindow();
    }

    private void initListeners() {
        confirmMoveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playViewCommandsController.onMove(movementCombo.getSelectedItem().toString());
            }
        });

        switchViewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playViewCommandsController.onSwitchView();
            }
        });
    }

    @Override
    public void switchView() {
//        DatabaseHandler.closeDBConnection();
        Runner.closeWindow();
        Runner.getWindow().dispose();
        Runner.closeDBConnection();
        new PlayConsole().initScreen();
    }

}
