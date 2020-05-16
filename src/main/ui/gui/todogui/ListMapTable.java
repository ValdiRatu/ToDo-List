package ui.gui.todogui;

import exceptions.InvalidNumberException;
import model.ListMap;

import javax.swing.table.AbstractTableModel;

// Idea taken from
// https://stackoverflow.com/questions/25418029/fill-jtable-from-json-object

public class ListMapTable extends AbstractTableModel {
    private ListMap listMap;
    private String[] columnNames = {"List Name", "Object"};

    public ListMapTable(ListMap listMap) {
        this.listMap = listMap;
    }

    @Override
    public int getRowCount() {
        return listMap.getHashMap().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // EFFECTS: Gets the values at the given index
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (columnIndex == 0) {
            try {
                value = listMap.getNameByIndex(rowIndex);
            } catch (InvalidNumberException e) {
                e.printStackTrace();
            }
        }
        if (columnIndex == 1) {
            try {
                value = listMap.getListByIndex(rowIndex);
            } catch (InvalidNumberException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
