package za.co.amjsolutions.swingy;

import za.co.amjsolutions.swingy.db.DatabaseHandler;
import za.co.amjsolutions.swingy.views.console.WelcomeConsole;
import za.co.amjsolutions.swingy.views.gui.WelcomeFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Runner {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static int EXIT_FAILURE = 1;
    private static Scanner scanner;
    private static JFrame jFrame;
    private static StringBuilder sb;

    public static void main(String[] args) {
        try {
            int len = args.length;
            String params = args[0].toLowerCase();
            if (len == 1 && (params.equals("console") || params.equals("gui"))) {
                DatabaseHandler.connectToDB();
                if (params.equals("console")) {
                    System.out.println("Starting the program in CONSOLE mode");
                    sleep(1000);
                    new WelcomeConsole().initScreen();
                } else if (params.equals("gui")) {
                    System.out.println("Starting the program in GUI mode");
                    sleep(1000);
                    new WelcomeFrame().initScreen();
                }
            } else {
                sb = new StringBuilder();
                sb.append("Invalid arguments!\nUSAGE: program [args]\nargs : [console | gui]\n")
                .append("\tconsole\t - \t Runs the SWINGY program as a console application.")
                .append("\tgui\t - \tRuns the SWINGY program with a Graphical User Interface.");
                System.out.println(sb.toString());
                System.exit(EXIT_FAILURE);
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public static JFrame getWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        if (jFrame == null) {
            jFrame = new JFrame();
            jFrame.setSize(WIDTH, HEIGHT);
            jFrame.setDefaultLookAndFeelDecorated(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setWindowListener();
        }
        return jFrame;
    }

    private static void setWindowListener() {
        getWindow().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDBConnection();
                super.windowClosing(e);
                System.exit(1);
            }
        });
    }

    public static void showWindow() {
        if (jFrame != null)
            jFrame.setVisible(true);
    }

    public static void closeWindow() {
        if (jFrame != null)
            jFrame.setVisible(false);
        closeDBConnection();
    }

    public static Scanner getScanner() {
        if (scanner != null) {
            return scanner;
        }
        scanner = new Scanner(System.in).useDelimiter(System.lineSeparator());
        return scanner;
    }

    public static void closeDBConnection() {
        DatabaseHandler.closeDBConnection();
        if (scanner != null)
            scanner.close();
    }
}
