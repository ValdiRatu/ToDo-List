package model;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;



import java.io.IOException;
import java.util.ArrayList;

public class ToDoListTest {
    private Item i;
    private Item i2;
    private ToDoList toDo;
    private ArrayList<Item> toDoList;

    @BeforeEach
    void setUp() {
        i = new Event();
        i.setName("test event");
        i.setTimeSlot(0);
        i2 = new Homework();
        i2.setName("test homework");
        i2.setTimeSlot(0);
        toDo = new ToDoList();
        toDo.setObserver(new ListMap());
    }

    @Test
    void testAddToDoList() {
        toDoList = toDo.getItems();
        assertNull(toDoList.get(0));
        try {
            toDo.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertEquals(toDoList.get(0), i);
        try {
            toDo.addToList(i2);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertEquals(toDoList.get(0), i2);
    }

    @Test
    void testRemove() {
        toDoList = toDo.getItems();
        try {
            toDo.addToList(i);
            i2.setTimeSlot(1);
            toDo.addToList(i2);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertEquals(toDoList.get(0), i);
        toDo.remove(i.getName());
        assertNull(toDoList.get(0));
        assertEquals(toDoList.get(1), i2);
    }

    @Test
    void testFind() {
        i.setName("test");
        assertFalse(toDo.findAndPrintItem("test"));
        try {
            toDo.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertTrue(toDo.findAndPrintItem("test"));
    }

    @Test
    void testViewItems() {
        assertFalse(toDo.viewAllItems());
        try {
            toDo.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertTrue(toDo.viewAllItems());
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
        Item i3 = new Event();
        i.setName("test 1");
        i2.setName("test 2");
        i2.setTimeSlot(1);
        i3.setName("this shouldn't show up");
        i3.setTimeSlot(1);
        try {
            toDo.addToList(i);
            toDo.addToList(i2);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        toDo.save();
        try {
            toDo.addToList(i3);
            toDo.addToList(i3);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        toDo.load();
        assertEquals(toDo.getItem(0).getName(), "test 1");
        assertEquals(toDo.getItem(1).getName(), "test 2");
        assertEquals(toDo.getItem(0).getItemType(), ItemType.EVENT);
        assertEquals(toDo.getItem(1).getItemType(), ItemType.HOMEWORK);
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        Item i = new Event();
        i.setName("test4");
        i.setDescription("test");
        i.setTimeSlot(4);
        try {
            toDo.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        toDo.save();
        toDo.load();
        assertEquals(toDo.getItem(4).getName(), "test4");
        assertEquals(toDo.getItem(4).getItemType(), ItemType.EVENT);
        toDo.remove(toDo.getItem(4).getName());
        assertNull(toDo.getItem(4));
        toDo.save();
    }

    @Test
    void testTooLongException() {
        i.setDuration(5);
        i.setTimeSlot(5);
        try {
            toDo.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        i.setTimeSlot(20);
        try {
            toDo.addToList(i);
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        } catch (TooLongException e) {

        }
            assertEquals(5, i.getDuration());
        }

    @Test
    void testNegativeDurationException() {
        i.setTimeSlot(1);
        i.setDuration(-1);
        try {
            toDo.addToList(i);
            fail();
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
        }
        assertEquals(-1, i.getDuration());
    }

}
