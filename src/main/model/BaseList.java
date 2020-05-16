package model;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;
import observer.Subject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public abstract class BaseList extends Subject implements Saveable, Loadable, Serializable {
    protected ArrayList<Item> items;
    protected static final int LIST_SIZE = 24;

    public BaseList() {
        items = new ArrayList<>();
        for (int n = 0; n < LIST_SIZE; n++) {
            items.add(n, null);
        }
    }

    // EFFECTS: returns the items of items
    public ArrayList<Item> getItems() {
        return items;
    }

    // EFFECTS: returns the item at index n
    public Item getItem(int n) {
        return items.get(n);
    }

    // MODIFIES: this
    // EFFECTS: adds i to this at index n
    public void addToList(Item i) throws TooLongException, NegativeOrZeroDurationException {
        if (i.getDuration() > LIST_SIZE - i.getTimeSlot()) {
            throw new TooLongException();
        }
        if (i.getDuration() <= 0) {
            throw new NegativeOrZeroDurationException();
        }
        for (int t = 0; t < i.getDuration(); t++) {
            items.set(i.getTimeSlot() + t, i);
        }
        notifyObserver(i);
    }

    // REQUIRES: item to exist in toDoList
    // MODIFIES: this
    // EFFECTS: removes item from this
    public void remove(String s) {
        for (int n = 0; n < LIST_SIZE; n++) {
            Item i = items.get(n);
            if (i != null && s.equals(i.getName())) {
                items.set(items.indexOf(i), null);
            }
        }
    }

    // EFFECTS: prints initialized items in toDoList
    public boolean viewAllItems() {
        HashSet<Item> already = new HashSet<>();
        Boolean b = false;
        for (Item i : items) {
            if (i != null && !already.contains(i)) {
                printItemMessage(i);
                b = true;
                already.add(i);
            }
        }
        return b;
    }

    private void printItemMessage(Item i) {
        System.out.println("Time: " + i.getTimeSlot() + ", To Do: " + i.getName() + "\n");
    }

    // EFFECTS: finds item i and prints it
    public Boolean findAndPrintItem(String s) {
        HashSet<Item> already = new HashSet<>();
        Boolean b = false;
        for (Item i : items) {
            if (i != null && s.equals(i.getName()) && !already.contains(i)) {
                i.print();
                already.add(i);
                b = true;
            }
        }
        return b;
    }

    // Found this save and load code on youtube
    // https://www.youtube.com/watch?v=bRuxXAF-Ysk

    @Override
    // EFFECTS: saves the current state of ToDoListUI
    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt"));
        out.writeObject(this);
    }

    // https://stackoverflow.com/questions/10281370/see-if-file-is-empty

    @Override
    // MODIFIES: this
    // EFFECTS: loads the previous state of ToDoListUI
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(
                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt");
        if (file.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt"));
            BaseList newBaseList = (BaseList) in.readObject();
            this.items = newBaseList.items;
        }
    }

//    @Override
//    // MODIFIES: this
//    // EFFECTS: loads the previous state of ToDoListUI
//    public void load() {
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
//                    "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\save.txt"));
//            BaseList newList = (BaseList) in.readObject();
//            this.items = newList.items;
//        } catch (IOException e) {
//        } catch (ClassNotFoundException e) {
//            System.out.println("Save file is corrupted");
//        }
//    }

    // MODIFIES: this
    // EFFECTS: resets the list
    public void blankList() {
        items = new ArrayList<>();
        for (int n = 0; n < LIST_SIZE; n++) {
            items.add(n, null);
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof BaseList)) {
//            return false;
//        }
//        BaseList baseList = (BaseList) o;
//        return Objects.equals(items, baseList.items);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(items);
//    }
}
