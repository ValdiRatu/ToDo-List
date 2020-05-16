package ui;

import exceptions.NegativeOrZeroDurationException;
import exceptions.TooLongException;
import model.Event;
import model.Homework;
import model.Item;
import model.ListMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListUITest {
    private ToDoListUI toDo;
    private Scanner reader;
    private Item i;
    private Item i2;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        toDo = new ToDoListUI();
        toDo.toDoList.setObserver(new ListMap());
        reader = new Scanner(System.in);
        i = new Event();
        i.setName("test event");
        i2 = new Homework();
        i2.setName("test homework");

    }
//    @Test
//    void testAddingItem() throws IOException {
//        String data = "1" + "\ntest" + "\nthis is a test??" + "\n12" + "\n2" + "\n-1";
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        reader = new Scanner(System.in);
//        toDo.chooseOperation(reader, "1");
//        assertTrue(toDo.toDoList.findAndPrintItem("test"));
//        toDo.toDoList.remove("test");
//        toDo.toDoList.save();
//    }

    @Test
    void testViewToDO() throws IOException, ClassNotFoundException {
        toDo.toDoList.blankList();
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader ,  "2");
        assertFalse(toDo.viewToDo());
        try {
            toDo.toDoList.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        assertTrue(toDo.viewToDo());
    }

    @Test
    void testViewEvent() throws IOException {
        i.setName("test");
        try {
            toDo.toDoList.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "3");
        assertTrue(toDo.toDoList.findAndPrintItem("test"));
        toDo.toDoList.remove("test");
    }

    @Test
    void testViewHomework() throws IOException {
        i2.setName("test");
        try {
            toDo.toDoList.addToList(i2);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "3");
        assertTrue(toDo.toDoList.findAndPrintItem("test"));
        toDo.toDoList.remove("test");
    }

    @Test
    void testRemoveFromToDo() throws IOException {
        try {
            toDo.toDoList.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test event";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "4");
        assertNull(toDo.toDoList.getItem(1));
    }

    @Test
    void testModifyName() throws IOException {
        Item i = new Event();
        i.setName("test");
        i.setDescription("this is a test");
        i.setTimeSlot(12);
        try {
            toDo.toDoList.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test" + "\n1" + "\nmodified name";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "5");
        assertFalse(toDo.toDoList.findAndPrintItem("test"));
        assertTrue(toDo.toDoList.findAndPrintItem("modified name"));
    }

    @Test
    void testModifyDescription() throws IOException {
        Item i3 = new Homework();
        i3.setName("test");
        i3.setDescription("this is a test");
        i3.setCourse("CPSC 210");
        i3.setTimeSlot(12);
        try {
            toDo.toDoList.addToList(i3);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test" + "\n2" + "\nmodified description";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "5");
        assertEquals(toDo.toDoList.getItem(i3.getTimeSlot()).getDescription(), "modified description");
    }

    @Test
    void testModifyTime() throws IOException {
        Item i = new Event();
        i.setName("test");
        i.setTimeSlot(12);
        try {
            toDo.toDoList.addToList(i);
        } catch (TooLongException e) {
            fail();
        } catch (NegativeOrZeroDurationException e) {
            fail();
        }
        String data = "test" + "\n3" + "\n15";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.chooseOperation(reader, "5");
        assertEquals(i.getTimeSlot(), 15);
        toDo.toDoList.remove("test");
        toDo.toDoList.save();
    }

    @Test
    void testEventTime() {
        String data = "12";
        i.setDuration(2);
        i.setTimeSlot(12);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        reader = new Scanner(System.in);
        toDo.askForTime(reader, i);
        assertEquals(i, toDo.toDoList.getItem(i.getTimeSlot()));
        assertEquals(i, toDo.toDoList.getItem(i.getTimeSlot()+1));
    }
}
