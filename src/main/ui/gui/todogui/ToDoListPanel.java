package ui.gui.todogui;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;
import model.*;
import model.Event;
import ui.gui.DregerButton;
import ui.gui.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ToDoListPanel {

    private ToDoToolBar toolBar;
    private Container container;
    private ViewJTable table;
    private JPanel toDoListPanel;


    public ToDoListPanel(ListMap listMap, Container container) throws IOException {
        this.container = container;
        toDoListPanel = new JPanel();
        toDoListPanel.setLayout(new BorderLayout());
        toDoListPanel.setBackground(new Color(50, 230, 100));
        GridBagConstraints gc = new GridBagConstraints();

        toolBar = new ToDoToolBar();
        toDoListPanel.add(toolBar, BorderLayout.NORTH);

        toolBar.setToDoListener(new ToDoListener() {
            // EFFECTS: calls an operation based on button pressed
            @Override
            public void popUp(String name) throws IOException {
                buttonOperation(name);
                listMap.save();
            }
        });

        table = new ViewJTable(listMap);
        toDoListPanel.add(table);

        UIManager ui = new UIManager();
        ui.put("Panel.background", new Color(60, 63, 65));
        ui.put("OptionPane.background", new Color(60, 63, 65));
        ui.put("OptionPane.messageForeground", Color.white);
    }

    public JPanel getToDoListPanel() {
        return toDoListPanel;
    }

    private void buttonOperation(String name) {
        switch (name) {
            case "add":
                createPopUp("add");
                break;
            case "remove":
                removeItem();
                break;
            case "modify":
                createPopUp("modify");
                break;
            default:
                break;
        }
    }

    private void createPopUp(String operation) {
        JPanel addPopUp = new JPanel();
        JTextField name = new JTextField(5);
        JTextField description = new JTextField(5);
        JTextField time = new JTextField(5);
        JTextField duration = new JTextField(5);
        JTextField course = new JTextField(5);
        String[] types = {"homework", "event"};
        JComboBox type = new JComboBox(types);
        initializingPopUp(addPopUp, name, description, time, duration, course, type);

        JButton addItem = createPopUpButton("add item");
        JButton cancel = createPopUpButton("cancel");

        if (operation.equals("add")) {
            popUp(addPopUp, name, description, time, duration, course, type, addItem, cancel);
        }
        if (operation.equals("modify")) {
            modifyNotNull(addPopUp, name, description, time, duration, course, type, addItem, cancel);
        }
    }

    private JButton createPopUpButton(String field) {
        JButton button = new DregerButton(field, new Color(60, 63, 65));
        initializeButtonInteraction(button);
        return button;
    }

    private void modifyNotNull(JPanel addPopUp, JTextField name, JTextField description, JTextField time,
                               JTextField duration, JTextField course, JComboBox type, JButton addItem,
                               JButton cancel) {
        if (table.getSelectedItem() != null) {
            modifyPopUp(addPopUp, name, description, time, duration, course, type, addItem, cancel);
        }
    }


    // https://stackoverflow.com/questions/14591089/joptionpane-passing-custom-buttons
    private void initializeButtonInteraction(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane =  getOptionPane((JComponent)e.getSource());
                pane.setValue(button);
            }
        });
    }


    private void modifyPopUp(JPanel addPopUp, JTextField name, JTextField description, JTextField time,
                             JTextField duration, JTextField course, JComboBox type, JButton addButton,
                             JButton cancelButton) {
        name.setText(table.getSelectedItem().getName());
        description.setText(table.getSelectedItem().getDescription());
        time.setText(Integer.toString(table.getSelectedItem().getTimeSlot()));
        duration.setText(Integer.toString(table.getSelectedItem().getDuration()));
        course.setText(table.getSelectedItem().getCourse());
        if (table.getSelectedItem().getItemType() == ItemType.HOMEWORK) {
            type.setSelectedItem("homework");
        } else {
            type.setSelectedItem("event");
        }
        removeItem();
        popUp(addPopUp, name, description, time, duration, course, type, addButton, cancelButton);
    }

    private void popUp(JPanel addPopUp, JTextField name, JTextField description, JTextField time, JTextField duration,
                       JTextField course, JComboBox type, JButton addButton, JButton cancelButton) {
        int result = JOptionPane.showOptionDialog(null,
                addPopUp, "Please enter the Item information", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon(), new Object[]{addButton, cancelButton}, null);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int timeSlot = Integer.parseInt(time.getText());
                popUpSwitch(name, description, duration, course, type, timeSlot);
                table.update();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid entry");
            }
        }
    }



    private void popUpSwitch(JTextField name, JTextField description, JTextField duration, JTextField course,
                             JComboBox type, int timeSlot) throws TooLongException, NegativeOrZeroDurationException {
        switch ((String)type.getSelectedItem()) {
            case "homework":
                createHomework(name, description, course, timeSlot);
                break;
            case "event":
                createEvent(name, description, duration, timeSlot);
                break;
            default: break;
        }
    }

    private void createHomework(JTextField name, JTextField description, JTextField course, int timeSlot)
            throws TooLongException, NegativeOrZeroDurationException {
        Item i1 = new Homework(name.getText(), description.getText(),
                timeSlot, course.getText());
        table.getCurrentToDoList().addToList(i1);
        container.updateStatsPanel();
    }

    private void createEvent(JTextField name, JTextField description, JTextField duration, int timeSlot)
            throws TooLongException, NegativeOrZeroDurationException {
        int durationAmount = Integer.parseInt(duration.getText());
        Item i2 = new Event(name.getText(), description.getText(),
                timeSlot, durationAmount);
        table.getCurrentToDoList().addToList(i2);
        container.updateStatsPanel();
    }

    private void initializingPopUp(JPanel addPopUp, JTextField name, JTextField description, JTextField time,
                                   JTextField duration, JTextField course, JComboBox type) {
        JLabel nameLabel = new JLabel("Name: ");
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel timeLabel = new JLabel("Time: ");
        JLabel durationLabel = new JLabel("Duration: ");
        JLabel courseLabel = new JLabel("Course: ");
        JLabel typeLabel = new JLabel("Type: ");
        addingComponentsToPopUp(addPopUp, nameLabel, name);
        addingComponentsToPopUp(addPopUp, descriptionLabel, description);
        addingComponentsToPopUp(addPopUp, timeLabel, time);
        addingComponentsToPopUp(addPopUp, durationLabel, duration);
        addingComponentsToPopUp(addPopUp, courseLabel, course);
        addingComponentsToPopUp(addPopUp, typeLabel, type);
    }

    private void addingComponentsToPopUp(JPanel addPopUp, JLabel label, JComponent field) {
        label.setFont(DregerButton.FONT);
        label.setForeground(Color.white);
        label.setBackground(new Color(60,63,65));
        field.setFont(DregerButton.FONT);
        field.setBackground(new Color(60,63,65));
        field.setForeground(Color.white);
        addPopUp.add(label);
        addPopUp.add(field);
        addPopUp.add(Box.createHorizontalStrut(15));
    }

    private void removeItem() {
        if (table.getSelectedItem() != null) {
            table.getCurrentToDoList().remove(table.getSelectedItem().getName());
            table.update();
        }
    }

    // https://coderanch.com/t/341219/java/JDialogBox
    private JOptionPane getOptionPane(Component c) {
        while ((c = c.getParent()) != null) {
            if (c instanceof JOptionPane) {
                return (JOptionPane) c;
            }
        }
        return null;
    }
}
