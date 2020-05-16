package model;

public class Event extends Item {

    public Event() {
        super();
        this.type = ItemType.EVENT;
        this.course = "";
    }

    public Event(String name, String description, int timeSlot, int duration) {
        super(name, description, timeSlot);
        this.course = "";
        this.duration = duration;
        this.type = ItemType.EVENT;
    }

    @Override
    public void setDuration(int n) {
        this.duration = n;
    }

    @Override
    public void setCourse(String s) {

    }

    // EFFECTS: prints the event details
    public void print() {
        super.print();
        System.out.println("Time: " + timeSlot + "-" + (timeSlot + duration));
    }
}
