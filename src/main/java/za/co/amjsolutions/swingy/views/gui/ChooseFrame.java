package za.co.amjsolutions.swingy.views.gui;

import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.ChooseViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.ChooseViewCommands;
import za.co.amjsolutions.swingy.views.console.ChooseConsole;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseFrame extends JFrame implements ChooseViewCommands {
    private JPanel chooseFrame;
    private JList<String> heroList;
    private JEditorPane infoPane;
    private JButton selectHeroBtn;
    private JButton createHeroBtn;
    private JButton switchViewBtn;
    private ChooseViewCommandsController chooseViewCommandsController;
    private int selectedIndex;

    @Override
    public void initScreen() {
        chooseViewCommandsController = new ChooseViewCommandsController(this);
        createUIComponents();
        initListeners();
    }

    @Override
    public void updateViewScreen(String info) {
        infoPane.setText(info);
    }

    @Override
    public void startGameView() {
        hideFrame();
        new PlayFrame().initScreen();
    }

    @Override
    public void createHeroView() {
        hideFrame();
        new CreateFrame().initScreen();
    }

    @Override
    public void displayOutput(String message) {
        JOptionPane.showMessageDialog(Runner.getWindow(), message);
    }

    @Override
    public void switchView() {
        hideFrame();
        new ChooseConsole().initScreen();
    }

    private void createUIComponents() {
        String[] heroes = chooseViewCommandsController.getAllHeroesFromDB();
        heroList.setListData(heroes);
        if (heroes.length == 0)
            infoPane.setText("No previously created Heroes found!");
        this.setVisible(true);
        Runner.getWindow().setContentPane(chooseFrame);
        Runner.getWindow().revalidate();
        Runner.showWindow();
    }

    private void initListeners() {
        heroList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (heroList.getSelectedIndex() != -1) {
                        chooseViewCommandsController.onChooseHeroSelection(heroList.getSelectedIndex());
                        selectHeroBtn.setEnabled(true);
                        selectedIndex = heroList.getSelectedIndex();
                    } else {
                        selectHeroBtn.setEnabled(false);
                    }
                }
            }
        });

        selectHeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedIndex);
                chooseViewCommandsController.onChooseHeroView(selectedIndex);
            }
        });

        createHeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseViewCommandsController.onCreateHeroView();
            }
        });

        switchViewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseViewCommandsController.onSwitchView();
            }
        });
    }

    private void hideFrame() {
        this.setVisible(false);
    }

}
