package ui.gui.todogui;

import ui.gui.DregerButton;
import ui.gui.SwitchListener;
import ui.gui.Tool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ToDoToolBar extends Tool {
    private Color toolBarColor = new Color(230, 178, 34);
    private ToDoListener toDoListener;

    public ToDoToolBar() {
        setLayout(new FlowLayout());
        setBackground(toolBarColor);
        createButtons();
        initializeButtons();
    }

    private void createButtons() {
        DregerButton add = new DregerButton("add an item", toolBarColor);
        DregerButton remove = new DregerButton("remove selected item", toolBarColor);
        DregerButton modify = new DregerButton("modify selected item", toolBarColor);
        buttons.add(add);
        buttons.add(remove);
        buttons.add(modify);
    }

    public void setToDoListener(ToDoListener listener) {
        this.toDoListener = listener;
    }


    // EFFECTS: calls listener when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        DregerButton clicked = (DregerButton)e.getSource();
        try {
            if (toDoListener != null) {
                if (clicked == buttons.get(0)) {
                    toDoListener.popUp("add");
                }
                if (clicked == buttons.get(1)) {
                    toDoListener.popUp("remove");
                }
                if (clicked == buttons.get(2)) {
                    toDoListener.popUp("modify");
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
