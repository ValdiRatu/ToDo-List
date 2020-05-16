package model;

import exceptions.InvalidNumberException;
import exceptions.NoListException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoListUI;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ListMapTest {
    ListMap lm;

    @BeforeEach
    void setUp() {
        lm = new ListMap();
    }

    @AfterEach
    void setDown() throws IOException {
        lm.removeList("test 1");
        lm.put("ToDoList", new ToDoList());
        lm.save();
    }

    @Test
    void testPut() throws IOException {
        try {
            lm.put("test 1", new ToDoList());
            assertEquals(lm.getHashMap().size(), 1);
            assertEquals(lm.getNameByIndex(0), "test 1");
        } catch (InvalidNumberException e) {
            fail();
        }
    }

    @Test
    void testGetListNoException() throws IOException {
        lm.put("test 1", new ToDoList());
        ToDoList selected = null;
        try {
            selected = lm.getList("test 1");
        } catch (NoListException e) {
            fail();
        }
    }

    @Test
    void testGetListNoListExceptionThrown() throws IOException {
        lm.put("test 1", new ToDoList());
        try {
            lm.getList("Does Not Exist");
            fail();
        } catch (NoListException e) {
            // expected
        }
    }

    @Test
    void testRemoveList() throws IOException {
        lm.put("test 1", new ToDoList());
        lm.removeList("test 1");
        assertNull(lm.getHashMap().get("test 1"));
    }

    @Test
    void testSaveAndLoad() throws IOException, ClassNotFoundException {
        lm.put("test 1", new ToDoList());
        lm.save();
        lm.put("test 2", new ToDoList());
        lm.load();
        assertNull(lm.getHashMap().get("test 2"));
    }
}
