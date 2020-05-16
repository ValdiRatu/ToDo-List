package model;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Item implements Serializable {

    protected String name;
    protected ItemType type;
    protected String description;
    protected int timeSlot;
    protected Boolean isDone;
    protected int duration;
    protected String course;

// constructors

    public Item() {
        this.name = "";
        this.type = null;
        this.description = "";
        this.isDone = false;
        this.duration = 1;
    }

    public Item(String name, String description, int timeSlot) {
        this.name = name;
        this.description = description;
        this.timeSlot = timeSlot;
    }



    // setters

    // MODIFIES: this
    // EFFECTS: sets name of this
    public void setName(String s)  {
        name = s;
    }

    // MODIFIES: this
    // EFFECTS: sets description of this
    public void setDescription(String s) {
        description = s;
    }

    // MODIFIES: this
    // EFFECTS: sets timeSlot of this
    public void setTimeSlot(int i) {
        timeSlot = i;
    }

    // MODIFIES: this
    // EFFECTS: sets duration
    public abstract void setDuration(int n);

    // MODIFIES: this
    // EFFECTS: sets course name
    public abstract void setCourse(String s);




    // getters

    // EFFECTS: returns name of this
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns type of this
    public ItemType getItemType() {
        return this.type;
    }

    // EFFECTS: returns description of this
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns timeSlot of this
    public int getTimeSlot() {
        return this.timeSlot;
    }

    // EFFECTS: returns duration
    public int getDuration() {
        return this.duration;
    }

    // EFFECTS: returns course
    public String getCourse() {
        return this.course;
    }

    // EFFECTS: prints the specifications of this
    public void print() {
        System.out.println("\nName: " + name);
        System.out.println("Type: " + type);
        System.out.println("Description: " + description);
    }

}
