package model;

import exceptions.InvalidNumberException;
import exceptions.NoListException;
import observer.Observer;
import observer.Subject;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ListMap implements Saveable, Loadable, Serializable, Observer {
    private LinkedHashMap<String, ToDoList> differentLists;
    private int homeworkCounter = 0;
    private int eventCounter = 0;

    public ListMap() {
        differentLists = new LinkedHashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: inserts a new items into map
    public void put(String name, ToDoList list) {
        differentLists.put(name, list);
        addSubject(list);
    }

    public LinkedHashMap<String, ToDoList> getHashMap() {
        return differentLists;
    }

    public int getHomeworkCounter() {
        return homeworkCounter;
    }

    public int getEventCounter() {
        return eventCounter;
    }

    // EFFECTS: returns items with given name,
    //          else throws NoListException
    public ToDoList getList(String name) throws NoListException {
        if (!differentLists.containsKey(name)) {
            throw new NoListException();
        }
        return differentLists.get(name);

    }


    // EFFECTS: gets the ToDoList via index
    public ToDoList getListByIndex(int index) throws InvalidNumberException {
        if (index > differentLists.size()) {
            throw new InvalidNumberException();
        }
        String name = (String)differentLists.keySet().toArray()[index];
        return differentLists.get(name);
    }

    // EFFECTS: gets the name via index
    public String getNameByIndex(int index) throws InvalidNumberException {
        if (index > differentLists.size()) {
            throw new InvalidNumberException();
        }
        return (String)differentLists.keySet().toArray()[index];
    }

    // MODIFIES: this
    // EFFECTS: removes items from map given its name
    public void removeList(String name) {
        differentLists.remove(name);
    }

    // Found this save and load code on youtube
    // https://www.youtube.com/watch?v=bRuxXAF-Ysk

    @Override
    // EFFECTS: saves the current state of ListMap
    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt"));
        out.writeObject(this);
    }

    // https://stackoverflow.com/questions/10281370/see-if-file-is-empty
    @Override
    // MODIFIES: this
    // EFFECTS: loads the previous state of ListMap
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(
                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt");
        if (file.length() > 0) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt"));
            ListMap newListMap = (ListMap) in.readObject();
            this.differentLists = newListMap.differentLists;
            this.homeworkCounter = newListMap.homeworkCounter;
            this.eventCounter = newListMap.eventCounter;
        }
    }

    // MODIFIES: s
    // EFFECTS: sets the subject's observer to this
    @Override
    public void addSubject(Subject s) {
        s.setObserver(this);
    }


    // MODIFIES: this
    // EFFECTS: increments counter
    @Override
    public void update(Item i) {
        if (i.getItemType() == ItemType.HOMEWORK) {
            homeworkCounter++;
        } else {
            eventCounter++;
        }
    }
}
