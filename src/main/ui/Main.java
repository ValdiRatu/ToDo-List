package ui;


import org.json.simple.parser.ParseException;
import ui.gui.Container;

import javax.swing.*;
import java.io.IOException;

public class Main {
    // EFFECTS: starts the program, loading a ListMapUI if there already is one
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
//        ListMapUI listMapUI = new ListMapUI();
//        listMapUI.load();
//        Scanner reader = new Scanner(System.in);
//
//        while (true) {
//            printListMapOptions();
//            String operation = reader.nextLine();
//            if (operation.equals("-1")) {
//                break;
//            } else {
//                listMapUI.chooseOperation(reader, operation);
//            }
//        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Container();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // EFFECTS: prints ListMap options
    private static void printListMapOptions() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Press [1] to interact with your lists");
        System.out.println("Press [2] to add a new list");
        System.out.println("Press [3] to remove a list");
        System.out.println("Press [4] for summary of your items");
        System.out.println("Press [5] for some fun facts");
        System.out.println("Press [-1] to end the program");
    }

}
