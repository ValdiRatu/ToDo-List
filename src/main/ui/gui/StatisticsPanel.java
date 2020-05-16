package ui.gui;

import model.ListMap;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private ListMap listMap;
    JTextArea homework;
    JTextArea event;

    public StatisticsPanel(ListMap listMap, Container container) {
        this.listMap = listMap;
        GridLayout grid = new GridLayout(0,2);
        setLayout(grid);
        setBackground(Panels.PANEL_COLOR);
        setBorder(BorderFactory.createLineBorder(new Color(130, 133, 135), 5));


        JPanel left = new JPanel();
        left.setBackground(Panels.PANEL_COLOR);
        add(left);
        homework = new JTextArea();
        homework.setFont(Container.FONT);
        homework.append("Num of homework: " + listMap.getHomeworkCounter());
        left.add(homework);

        JPanel right = new JPanel();
        right.setBackground(Panels.PANEL_COLOR);
        add(right, BorderLayout.EAST);
        event = new JTextArea();
        event.setFont(Container.FONT);
        event.append("Num of events: " + listMap.getEventCounter());
        right.add(event);
    }

    // EFFECTS: prints out the statics
    public void update() {
        homework.setText("Num of homework: " + listMap.getHomeworkCounter());
        event.setText("Num of events: " + listMap.getEventCounter());
    }

}
