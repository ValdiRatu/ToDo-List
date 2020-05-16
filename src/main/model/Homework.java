package model;

public class Homework extends Item {

    public Homework() {
        super();
        this.type = ItemType.HOMEWORK;
    }

    public Homework(String name, String description, int timeSlot, String course) {
        super(name, description, timeSlot);
        this.course = course;
        this.type = ItemType.HOMEWORK;
        duration = 1;
    }

    @Override
    public void setDuration(int n) {
    }

    @Override
    public void setCourse(String s) {
        this.course = s;
    }

    // EFFECTS: prints the homework details
    public void print() {
        super.print();
        System.out.println("Course: " + course);
    }
}
