package za.co.amjsolutions.swingy.views.gui;

import lombok.Getter;
import za.co.amjsolutions.swingy.Runner;
import za.co.amjsolutions.swingy.controllers.WelcomeViewCommandsController;
import za.co.amjsolutions.swingy.interfaces.WelcomeViewCommands;
import za.co.amjsolutions.swingy.views.console.WelcomeConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class WelcomeFrame extends JFrame implements WelcomeViewCommands {
    private WelcomeViewCommandsController welcomeViewCommandsController;
    private JPanel welcomeFrame = new JPanel();
    private JLabel welcomeLbl = new JLabel();
    private JButton switchBtn = new JButton();
    private JTextArea gameRulesTxt = new JTextArea();
    private JButton createHeroBtn = new JButton();
    private JButton selectHeroBtn = new JButton();

    @Override
    public void initScreen() {
        welcomeViewCommandsController = new WelcomeViewCommandsController(this);
        createUIComponents();
        initListeners();
    }

    @Override
    public void createHeroView() {
        this.setVisible(false);
        new CreateFrame().initScreen();
    }

    @Override
    public void chooseHeroView() {
        this.setVisible(false);
        new ChooseFrame().initScreen();
    }

    @Override
    public void switchView() {
        Runner.closeWindow();
        new WelcomeConsole().initScreen();
    }

    private void createUIComponents() {
        this.setVisible(true);
        Runner.getWindow().setContentPane(welcomeFrame);
        Runner.getWindow().revalidate();
        Runner.showWindow();
    }

    private void initListeners() {
        createHeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeViewCommandsController.onCreateHero();
            }
        });
        selectHeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeViewCommandsController.onChooseHero();
            }
        });
        switchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Switching to Console view...");
                welcomeViewCommandsController.onSwitchViews();
            }
        });
    }
}
