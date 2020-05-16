package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panels extends JPanel {
    public static final Color PANEL_COLOR = new Color(255, 255, 250);

    private JPanel currentPanel;
    private List<JPanel> panels;
    private CardLayout cl = new CardLayout();

    public Panels() {
        panels = new ArrayList<>();
        currentPanel = this;
        setLayout(cl);
        setBackground(PANEL_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: adds a JPanel to the card layout
    public void addPanel(JPanel panel, String name) {
        if (!panels.contains(panel)) {
            panels.add(panel);
            add(panel, name);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the current viewed panel
    public void setCurrentPanel(String name) {
        cl.show(this, name);
    }

}
