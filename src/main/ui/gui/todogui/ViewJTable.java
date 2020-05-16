package ui.gui.todogui;

import model.Item;
import model.ListMap;
import model.ToDoList;
import ui.gui.DregerButton;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;

public class ViewJTable extends JPanel {
    private JTable listMapJTable;
    private JTable toDoListJTable;
    private ToDoList currentToDoList;
    private ListMap listMap;
    private DefaultTableCellRenderer center;
    private Item selectedItem;
    private TableColumnModel toDoListColumnModel;

    public ViewJTable(ListMap listMap) {
        this.listMap = listMap;
        setBackground(Color.white);
        setLayout(new BorderLayout());

        // listMapJTable
        ListMapTable listMapTable = new ListMapTable(listMap);
        listMapJTable = new JTable(listMapTable);
        listMapJTable.setFont(DregerButton.FONT);
        listMapJTable.selectAll();
        listMapJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel listMapColumnModel = listMapJTable.getColumnModel();
        listMapColumnModel.removeColumn(listMapColumnModel.getColumn(1));
        listMapJTable.setRowSelectionInterval(0,0);

        add(listMapJTable, BorderLayout.WEST);

        //toDoListJTable
        currentToDoList = (ToDoList)listMapJTable.getModel().getValueAt(0,1);
        ListTable listTable = new ListTable(currentToDoList);
        toDoListJTable = new JTable(listTable);
        toDoListJTable.setFont(DregerButton.FONT);
        JScrollPane pane = new JScrollPane(toDoListJTable);
        pane.setFont(DregerButton.FONT);
        add(pane);
        toDoListJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        toDoListJTable.setColumnSelectionAllowed(false);


        center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        toDoListJTable.getColumnModel().getColumn(0).setCellRenderer(center);

        toDoListColumnModel = toDoListJTable.getColumnModel();
        toDoListColumnModel.removeColumn(toDoListColumnModel.getColumn(5));

        // selecting toDoListJTable
        ListSelectionModel toDoSelect = toDoListJTable.getSelectionModel();
        toDoSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        toDoSelect.addListSelectionListener(new ListSelectionListener() {
            // MODIFIES: this
            // EFFECTS: changes the selectedITem into currently selected item
            //          if an error occurs, item is set to null or previously selected item
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    selectedItem = (Item) toDoListJTable.getModel().getValueAt(toDoListJTable.getSelectedRow(), 5);
                } catch (NullPointerException exc) {
                    selectedItem = null;
                } catch (ArrayIndexOutOfBoundsException exc2) {
                    selectedItem = selectedItem;
                }
            }
        });




        // selecting listMapJTable
        ListSelectionModel listMapSelect = listMapJTable.getSelectionModel();
        listMapSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMapSelect.addListSelectionListener(new ListSelectionListener() {
            // MODIFIES: this
            // EFFECTS: clears the selection in ToDoList table and updates the ToDoList table
            @Override
            public void valueChanged(ListSelectionEvent e) {
                toDoSelect.clearSelection();
                update();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes currentToDoList to selected ToDoList from listMap table
    public void update() {
        currentToDoList = (ToDoList)listMapJTable.getModel().getValueAt(listMapJTable.getSelectedRow(),1);
        toDoListJTable.setModel(new ListTable(currentToDoList));
        toDoListJTable.getColumnModel().getColumn(0).setCellRenderer(center);
        toDoListColumnModel = toDoListJTable.getColumnModel();
        toDoListColumnModel.removeColumn(toDoListColumnModel.getColumn(5));
    }

    public ToDoList getCurrentToDoList() {
        return currentToDoList;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }
}
