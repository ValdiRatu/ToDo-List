//package ui;
//
//import exceptions.NoListException;
//import model.*;
//import network.PokemonFacts;
//import observer.Observer;
//import observer.Subject;
//import org.json.simple.parser.ParseException;
//
//import java.io.*;
//import java.util.Iterator;
//import java.util.Objects;
//import java.util.Scanner;
//
//
//public class ListMapUI implements Saveable, Loadable, Serializable, Observer {
//    private ListMap listMap;
//    private int homeWork;
//    private int event;
//
//    public ListMapUI() throws IOException {
//        listMap = new ListMap();
//        this.homeWork = 0;
//        this.event = 0;
//        ToDoListUI init = new ToDoListUI();
//        listMap.put("ToDoList", init);
//        addSubject(init);
//    }
//
//    public int getHomeWork() {
//        return this.homeWork;
//    }
//
//    public int getEvent() {
//        return this.event;
//    }
//
//    public ListMap getListMap() {
//        return listMap;
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: chooses the operation on listMap
//    public void chooseOperation(Scanner reader, String operation) throws IOException, ParseException {
//        if (operation.equals("1")) {
//            print();
//            goThroughLists(reader);
//        }
//        if (operation.equals("2")) {
//            createNewList(reader);
//        }
//        if (operation.equals("3")) {
//            print();
//            removeList(reader);
//        }
//        if (operation.equals("4")) {
//            printFunMessage();
//        }
//        if (operation.equals("5")) {
//            PokemonFacts.start();
//        }
//        save();
//    }
//
//    private void printFunMessage() {
//        System.out.println("You have made " + this.homeWork + " homework items");
//        System.out.println("You have made " + this.event + " event items");
//        if (this.homeWork > 5) {
//            System.out.println("you sure have a lot of homework!");
//        } else if (this.event > 5) {
//            System.out.println("you sure are busy with events!");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: go through specific items
//    private void goThroughLists(Scanner reader) throws IOException {
//        try {
//            ToDoListUI selected = selectList(reader);
//            editList(reader, selected);
//        } catch (NoListException e) {
//            printInvalidListMessage();
//        }
//    }
//
//    // EFFECTS: prints "Not a valid items"
//    private void printInvalidListMessage() {
//        System.out.println("\nNot a valid list");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates a new items in ListMap
//    public void createNewList(Scanner reader) throws IOException {
//        System.out.println("\nWhat would you like to call this List?");
//        String name = reader.nextLine();
//        ToDoListUI next = new ToDoListUI();
//        listMap.put(name, next);
//        addSubject(next);
//    }
//
//    // EFFECTS: returns ToDoListUI given the name
//    private ToDoListUI selectList(Scanner reader) throws NoListException {
//        ToDoListUI selected;
//        String name = reader.nextLine();
//        selected = listMap.getList(name);
//        return selected;
//    }
//
//    // EFFECTS: prints out the lists in map
//    private void print() {
//        listMap.printListMap();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: removes items from map given name
//    private void removeList(Scanner reader) {
//        System.out.println("\nPlease select a items");
//        String name = reader.nextLine();
//        listMap.removeList(name);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: goes through specific items
//    private void editList(Scanner reader, ToDoListUI toDoUI) throws IOException {
//        System.out.println("\nWelcome to your list!");
//        while (true) {
//            printEditListMessage();
//            String operation = reader.nextLine();
//            if (operation.equals("-1")) {
//                save();
//                break;
//            } else {
//                toDoUI.chooseOperation(reader, operation);
//                save();
//            }
//        }
//    }
//
//    // EFFECTS: prints the available instructions
//    private void printEditListMessage() {
//        System.out.println("\nWhat would you like to do?");
//        System.out.println("Press [1] to add to your to-do list");
//        System.out.println("press [2] to view your to-do list");
//        System.out.println("press [3] to view a specific item in your to-do list");
//        System.out.println("press [4] to delete an item from to-do list");
//        System.out.println("press [5] To modify an item from to-do list");
//        System.out.println("press [-1] To exit");
//    }
//
//    // MODIFIES: s
//    // EFFECTS: sets this as s's observer
//    @Override
//    public void addSubject(Subject s) {
//        s.setObserver(this);
//    }
//
//    // EFFECTS: increases the item counter
//    @Override
//    public void update(Item i) {
//        if (i.getItemType() == ItemType.HOMEWORK) {
//            this.homeWork += 1;
//        } else {
//            this.event += 1;
//        }
//    }
//
//
//
//    // Found this save and load code on youtube
//    // https://www.youtube.com/watch?v=bRuxXAF-Ysk
//
//    @Override
//    // https://stackoverflow.com/questions/10281370/see-if-file-is-empty
//    // MODIFIES: this
//    // EFFECTS: loads the previous state of ListMap
//    public void load() throws IOException, ClassNotFoundException {
//        File file = new File(
//                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt");
//        if (file.length() > 0) {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
//                    "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt"));
//            ListMapUI newListMapUI = (ListMapUI) in.readObject();
//            this.listMap = newListMapUI.listMap;
//            this.homeWork = newListMapUI.homeWork;
//            this.event = newListMapUI.event;
//        }
//    }
//
//    @Override
//    // EFFECTS: saves the current state of ListMap
//    public void save() throws IOException {
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
//                "C:\\Users\\Valdi\\Documents\\CPSC 210\\project_c8g2b\\data\\saveMap.txt"));
//        out.writeObject(this);
//    }
//
//    // EFFECTS: changes equals and hashcode for this object so that having the same
//    //          ListMap means that it is the same ListMapUI
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof ListMapUI)) {
//            return false;
//        }
//        ListMapUI listMapUI = (ListMapUI) o;
//        return Objects.equals(listMap, listMapUI.listMap);
//    }
//
//    // EFFECTS: changes equals and hashcode for this object so that having the same
//    //          ListMap means that it is the same ListMapUI
//    @Override
//    public int hashCode() {
//        return Objects.hash(listMap);
//    }
//
//
//}
