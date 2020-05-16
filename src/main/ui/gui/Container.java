package ui.gui;

import model.*;
import observer.Observer;
import observer.Subject;
import ui.ToDoListUI;
import ui.gui.todogui.ToDoListPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

// Learned most of swing via
// https://www.youtube.com/watch?v=WRwPVZ4jmNY&list=WL&index=40&t=5205s


public class Container extends JFrame {
    private static final int WIDTH = 1920 / 2;
    private static final int HEIGHT = 1080 / 2;
    public static final Font FONT = new Font("Gill Sans MT",Font.BOLD,14);


    private ListMap listMap;
    private Panels panels;
    private StatisticsPanel stats;


    public Container() throws IOException, ClassNotFoundException {
        super("To-Do List");
        initializeListMap();
        initializeGraphics();

        ToolBar tool = new ToolBar();
        add(tool, BorderLayout.NORTH);
        tool.setSwitchListener(new SwitchListener() {
            // EFFECTS: switches the panel into a different panel based on button pressed
            @Override
            public void switchPanels(String name) {
                panels.setCurrentPanel(name);
            }
        });

        panels = new Panels();
        add(panels);

        ToDoListPanel toDoPanel = new ToDoListPanel(listMap, this);
        panels.addPanel(toDoPanel.getToDoListPanel(), "interact");

        ManagePanel managePanel = new ManagePanel(listMap);
        panels.addPanel(managePanel, "manage");

        stats = new StatisticsPanel(listMap, this);
        panels.addPanel(stats, "statistics");

    }

    private void initializeListMap() throws IOException, ClassNotFoundException {
        listMap = new ListMap();
        ToDoList init = new ToDoList();
        listMap.put("ToDoList", init);
        listMap.load();
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: updates the stats panel
    public void updateStatsPanel() {
        stats.update();
    }
}
