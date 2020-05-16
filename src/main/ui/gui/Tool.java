package ui.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Tool extends JPanel implements ActionListener {
    protected List<JButton> buttons = new ArrayList<>();

    protected void initializeButtons() {
        for (JButton b : buttons) {
            add(b);
            b.addActionListener(this);
        }
    }
}
