package ui.gui.todogui;

import model.Item;
import model.ToDoList;

import javax.swing.table.AbstractTableModel;

// Idea taken from
// https://stackoverflow.com/questions/25418029/fill-jtable-from-json-object

public class ListTable extends AbstractTableModel {
    private ToDoList toDo;
    private String[] columnName = {"Time", "Name", "Type", "Description", "Course", "item"};

    public ListTable(ToDoList toDo) {
        this.toDo = toDo;
    }

    @Override
    public int getRowCount() {
        return toDo.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int index) {
        return columnName[index];
    }


    // EFFECTS: gets the value at given index
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Item item = toDo.getItem(rowIndex);
        if (columnIndex == 0) {
            return value = rowIndex;
        }
        if (item == null) {
            return null;
        }
        value = getObject(columnIndex, value, item);
        return value;
    }

    // EFFECTS: helps get the right object at given index
    private Object getObject(int columnIndex, Object value, Item item) {
        switch (columnIndex) {
            case 1: value = item.getName();
                    break;
            case 2: value = item.getItemType();
                    break;
            case 3: value = item.getDescription();
                    break;
            case 4: value = item.getCourse();
                    break;
            case 5: value = item;
                    break;
            default: break;

        }
        return value;
    }
}

