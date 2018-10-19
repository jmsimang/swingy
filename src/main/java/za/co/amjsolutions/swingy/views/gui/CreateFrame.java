package za.co.amjsolutions.swingy.views.gui;

import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.CreateViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.CreateViewCommands;
import za.co.amjsolutions.swingy.views.console.CreateConsole;
import za.co.amjsolutions.swingy.views.console.WelcomeConsole;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFrame extends JFrame implements CreateViewCommands {
    private CreateViewCommandsController createViewCommandsController;
    private JPanel createFrame;
    private JLabel heroNameLbl;
    private JTextField heroNameTxt;
    private JLabel heroClassLbl;
    private JComboBox heroClassCombo;
    private JList heroClassLst;
    private JButton createHeroButton;
    private JButton backBtn;
    private JButton switchViewBtn;

    @Override
    public void initScreen() {
        createViewCommandsController = new CreateViewCommandsController(this);
        createUIComponents();
        initListeners();
    }

    @Override
    public void inputScreen() {
    }


    @Override
    public void displayOutput(String message) {
        JOptionPane.showMessageDialog(Runner.getWindow(), message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private void createUIComponents() {
        this.setVisible(true);
        Runner.getWindow().setContentPane(createFrame);
        Runner.getWindow().revalidate();
        Runner.showWindow();
    }

    private void initListeners() {
        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createViewCommandsController.onCreateHero(heroNameTxt.getText(), heroClassCombo.getSelectedItem().toString());
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createViewCommandsController.onGoBackToWelcome();
            }
        });
        switchViewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createViewCommandsController.onSwitchView();
            }
        });
    }

    @Override
    public void startGameView() {
        hideFrame();
        new PlayFrame().initScreen();
    }

    @Override
    public void welcomeScreen() {
        hideFrame();
        new WelcomeConsole().initScreen();
    }

    @Override
    public void switchView() {
        hideFrame();
        new CreateConsole().initScreen();
    }

    private void hideFrame() {
        this.setVisible(false);
    }

}
