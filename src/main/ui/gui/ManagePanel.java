package ui.gui;

import model.ListMap;
import model.ToDoList;
import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManagePanel extends JPanel {
    private ListMap listMap;

    JPanel showLists;
    JTextArea lists;
    JPanel managing;
    DregerButton addButton;
    DregerButton removeButton;

    public ManagePanel(ListMap listMap) {
        this.listMap = listMap;

        setLayout(new GridLayout(0, 2));

        showLists = new JPanel();
        showLists.setBackground(Panels.PANEL_COLOR);
        showLists.setBorder(BorderFactory.createLineBorder(new Color(90,93,95), 5));
        add(showLists);
        lists = new JTextArea();
        lists.setEditable(false);
        lists.setFont(DregerButton.FONT);
        update();
        showLists.add(lists);


        managing = new JPanel();
        managing.setLayout(new GridBagLayout());
        managing.setBackground(Panels.PANEL_COLOR);
        managing.setBorder(BorderFactory.createLineBorder(new Color(90,93,95), 5));
        add(managing);

        JTextField newList = new JTextField(10);
        newList.setFont(DregerButton.FONT);

        JTextField removeList = new JTextField(10);
        removeList.setFont(DregerButton.FONT);

        addButton = new DregerButton("add", Panels.PANEL_COLOR, new Color(60,63,65));
        removeButton = new DregerButton("remove", Panels.PANEL_COLOR, new Color(60,63,65));

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // ROW 1 /////////////////////////////////////////////////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        managing.add(newList);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        managing.add(addButton);

        // ROW 2 ////////////////////////////////////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        managing.add(removeList);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        managing.add(removeButton);


        addButton.addActionListener(new ActionListener() {
            // EFFECTS: adds a new ToDoList to the map and updates the text
            @Override
            public void actionPerformed(ActionEvent e) {
                listMap.put(newList.getText(), new ToDoList());
                newList.setText("");
                update();
                try {
                    listMap.save();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            // EFFECTS: removes a ToDoList from the map and updates the text
            @Override
            public void actionPerformed(ActionEvent e) {
                listMap.removeList(removeList.getText());
                removeList.setText("");
                update();
                try {
                    listMap.save();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        // lambda expressions are pretty cool

//        addButton.addActionListener(
//                (e) -> {
//                    try {
//                        listMap.put(newList.getText(), new ToDoListUI());
//                        showLists.revalidate();
//                    } catch (IOException exception) {
//                        System.out.println("error");
//                    }
//                }
//        );

    }

    private void update() {
        lists.setText("Your Lists: \n");
        for (String s : listMap.getHashMap().keySet()) {
            lists.append(s + "\n");
        }
    }

}
