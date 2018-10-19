package za.co.amjsolutions.swingy.controllers;

import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.listeners.SelectionBtn1Listener;
import za.co.amjsolutions.swingy.listeners.SelectionBtn2Listener;
import za.co.amjsolutions.swingy.listeners.SelectionList1Listener;
import za.co.amjsolutions.swingy.views.SelectionFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionFrameController {
    private SelectionFrame selectionFrame;
    private JList list1;
    private JList list2;
    private JButton button1;
    private JButton button2;

    public SelectionFrameController() {
        createUIComponents();
        initListeners();
    }

    public void showSelectionWindow() { selectionFrame.setVisible(true); }

    private void createUIComponents() {

        try {
            selectionFrame = new SelectionFrame();
            list1 = selectionFrame.getList1();
            list2 = selectionFrame.getList2();
            button1 = selectionFrame.getButton1();
            button2 = selectionFrame.getButton2();

            ArrayList<String> heroes = DatabaseHandler.selectAllHeroes();
            DefaultListModel heroModel = new DefaultListModel();
            for (int i = 0; i < heroes.size(); i++) {
                heroModel.addElement(heroes.get(i));
            }
            list1.setModel(heroModel);
        } catch (HeadlessException e) {
            System.err.println("An error occurred while creating the UI window:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initListeners()  {
        button1.addActionListener(new SelectionBtn1Listener());
        button2.addActionListener(new SelectionBtn2Listener());

        list1.addListSelectionListener(new SelectionList1Listener());
    }
}
