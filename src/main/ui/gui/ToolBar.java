package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToolBar extends Tool {
//    private Color toolBarColor = new Color(24, 162, 200);
    private Color toolBarColor = new Color(60, 63, 65);
    private SwitchListener switchListener;

    public ToolBar() {
        setLayout(new FlowLayout());
        setBackground(toolBarColor);
        createButtons();
        initializeButtons();
    }

    private void createButtons() {
        DregerButton interact = new DregerButton("Interact With Lists", toolBarColor);
        DregerButton manageLists = new DregerButton("manage your lists", toolBarColor);
        DregerButton viewStatistic = new DregerButton("view statistics", toolBarColor);
//        JButton pokemon = new JButton("fun fucts!");
        buttons.add(interact);
        buttons.add(manageLists);
        buttons.add(viewStatistic);
    }

    public void setSwitchListener(SwitchListener switchListener) {
        this.switchListener = switchListener;
    }


    // EFFECTS: calls the listener when action is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        DregerButton clicked = (DregerButton) e.getSource();
        if (switchListener != null) {
            if (clicked == buttons.get(0)) {
                switchListener.switchPanels("interact");

            }
            if (clicked == buttons.get(1)) {
                switchListener.switchPanels("manage");
            }
            if (clicked == buttons.get(2)) {
                switchListener.switchPanels("statistics");
            }
        }
    }


}
