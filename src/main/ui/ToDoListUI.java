package ui;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;
import model.*;
import model.ToDoList;
import observer.Subject;

import java.io.*;
import java.util.*;

public class ToDoListUI extends Subject implements Serializable {
    protected ToDoList toDoList;
    
    public ToDoListUI() throws IOException {
        toDoList = new ToDoList();
    }

    public ToDoListUI(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
    

    // EFFECTS: selects the operation for toDoList
    public void chooseOperation(Scanner reader, String operation) throws IOException {
        if (operation.equals("1")) {
            addToDo(reader);
        }
        if (operation.equals("2")) {
            viewToDo();
        }
        if (operation.equals("3")) {
            viewItem(reader);
        }
        if (operation.equals("4")) {
            removeFromToDo(reader);
        }
        if (operation.equals("5")) {
            modify(reader);
        }
    }

    // EFFECTS: Creates the item that will be added to toDOList
    // and asks for the to set the fields
    private void addToDo(Scanner reader) throws IOException {
        askForType(reader);
    }

    // MODIFIES: i
    // EFFECTS: sets the item type for i
    private void askForType(Scanner reader) {
        printAskForTypeMessage();
        String s = reader.nextLine();
        if (s.equals("1")) {
            Item i = new Event();
            initializeItem(reader, i);
            askForDuration(reader, i);
            notifyObserver(i);
        }
        if (s.equals("2")) {
            Item i = new Homework();
            initializeItem(reader, i);
            askForCourse(reader, i);
            notifyObserver(i);
        }
    }

    private void initializeItem(Scanner reader, Item i) {
        askForName(reader, i);
        askForDescription(reader, i);
        askForTime(reader, i);
    }

    private void printAskForTypeMessage() {
        System.out.println("\nwhat type of item do you want?");
        System.out.println("Press [1] for an 'event'");
        System.out.println("Press [2] for 'homework'");
    }

    // MODIFIES: i
    // EFFECTS: sets the name for i
    private void askForName(Scanner reader, Item i) {
        System.out.println("\nWhat would you like to call this " + i.getItemType() + " ?");
        String name = reader.nextLine();
        i.setName(name);

    }

    // MODIFIES: i
    // EFFECTS: sets the description for i
    private void askForDescription(Scanner reader, Item i) {
        System.out.println("\nPlease describe this " + i.getItemType());
        String description = reader.nextLine();
        i.setDescription(description);

    }

    // MODIFIES: this and i
    // EFFECTS: inserts i into toDoList at the wanted time
    // also sets timeSlot for i
    public void askForTime(Scanner reader, Item i) {
        System.out.println("\nSet the time [00-23]");
        String s = reader.nextLine();
        try {
            int time = Integer.parseInt(s);
            if (time >= 0 && time <= toDoList.getItems().size() - 1) {
                i.setTimeSlot(time);
                toDoList.addToList(i);
            } else {
                askForTime(reader, i);
            }
        } catch (NumberFormatException e) {
            askForTime(reader, i);
        } catch (TooLongException e) {
            tooLongExceptionResponse(reader, i);
        } catch (NegativeOrZeroDurationException e) {
            negativeDurationResponse(reader, i);
        }
    }

    private void tooLongExceptionResponse(Scanner reader, Item i) {
        System.out.println("\nThe event is too long, please change the duration");
        askForDuration(reader, i);
    }

    private void negativeDurationResponse(Scanner reader, Item i) {
        System.out.println("\nThe event is too long, please change the duration");
        askForDuration(reader, i);
    }

    // MODIFIES: i
    // EFFECTS: sets the course
    private void askForCourse(Scanner reader, Item i) {
        System.out.println("\nFor what course is this for?");
        i.setCourse(reader.nextLine());
    }


    // MODIFIES: i
    // EFFECTS: sets the duration
    private void askForDuration(Scanner reader, Item i) {
        System.out.println("\nWhat is the duration of the event?");
        String s = reader.nextLine();
        try {
            int n = Integer.parseInt(s);
            i.setDuration(n);
            toDoList.addToList(i);
        } catch (NumberFormatException e) {
            askForDuration(reader, i);
        } catch (NegativeOrZeroDurationException e) {
            negativeDurationResponse(reader, i);
        } catch (TooLongException e) {
            tooLongExceptionResponse(reader, i);
        }
    }

    // EFFECTS: prints out toDoList
    public Boolean viewToDo() {
        Boolean b = false;
        for (int n = 0; n < toDoList.getItems().size(); n++) {
            Item i = toDoList.getItems().get(n);
            if (i != null) {
                System.out.println("Time: " + n + ", To Do: " + i.getName() + "\n");
                b = true;
            } else {
                System.out.println("Time: " + n + " To Do: " + "N/A\n");
            }
        }
        return b;
    }


    // EFFECTS: prints the specification of item
    private void viewItem(Scanner reader) {
        toDoList.viewAllItems();
        System.out.println("\nplease select one of your items (enter the name of the item)");
        String s = reader.nextLine();
        toDoList.findAndPrintItem(s);
    }
    

    // MODIFIES: this
    // EFFECTS: removes item i from toDoList
    private void removeFromToDo(Scanner reader) {
        toDoList.viewAllItems();
        System.out.println("\nplease select one of your items (enter the name of the item)");
        String s = reader.nextLine();
        toDoList.remove(s);
    }

    // MODIFIES: this
    // EFFECTS: finds the item to modify
    private void modify(Scanner reader) throws IOException {
        toDoList.viewAllItems();
        System.out.println("\nplease select one of your items (enter the name of the item)");
        String s = reader.nextLine();
        Item i = null;
        for (Item j: toDoList.getItems()) {
            if (j != null && j.getName().equals(s)) {
                i = j;
            }
        }
        if (i != null) {
            modifyItem(i, reader);
        }
    }

    // MODIFIES: this
    // EFFECTS: asks the user to select which field to modify
    private void modifyItem(Item i, Scanner reader) throws IOException {
        printModifyItemMessage();
        chooseModifier(i, reader);
    }

    private void printModifyItemMessage() {
        System.out.println("Press [1] to modify name");
        System.out.println("Press [2] to modify description");
        System.out.println("Press [3] to modify time slot");
    }

    // MODIFIES: this
    // EFFECTS: modifies the given field
    private void chooseModifier(Item i, Scanner reader) throws IOException {
        String s = reader.nextLine();
        if (s.equals("1")) {
            System.out.println("\nenter new name");
            i.setName(reader.nextLine());
        }
        if (s.equals("2")) {
            System.out.println("\nenter new description");
            i.setDescription(reader.nextLine());
        }
        if (s.equals("3")) {
            toDoList.remove(i.getName());
            askForTime(reader, i);
        }
    }

    // EFFECTS: changes equals and hashcode so that having the same ToDoList
    //          means the same ToDoListUI
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDoListUI)) {
            return false;
        }
        ToDoListUI that = (ToDoListUI) o;
        return Objects.equals(toDoList, that.toDoList);
    }

    // EFFECTS: changes equals and hashcode so that having the same ToDoList
    //          means the same ToDoListUI
    @Override
    public int hashCode() {
        return Objects.hash(toDoList);
    }
    
}

