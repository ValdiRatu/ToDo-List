package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Homework;
import model.Item;
import model.ItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HomeworkTest {
    private Item i;


    @BeforeEach
    void setUp() {
        i = new Homework();
        i.setName("test");
        i.setDescription("test description");
        i.setCourse("CPSC 210");
        i.setTimeSlot(0);
    }

    @Test
    void testGetNSet() {
        i.setName("a");
        assertEquals("a", i.getName());
        i.setName("");
        assertEquals("", i.getName());
        assertEquals(ItemType.HOMEWORK, i.getItemType());
        i.setDescription("abc");
        assertEquals("abc", i.getDescription());
        i.setTimeSlot(2);
        assertEquals(2, i.getTimeSlot());
        i.setDuration(2);
        assertEquals(i.getDuration(), 1);
        assertEquals(i.getCourse(), "CPSC 210");
    }

    @Test
    void testConstructor2() {
        i = new Homework("test2", "test", 12, "cpsc");
        assertEquals(i.getName(), "test2");
        assertEquals(i.getDescription(), "test");
        assertEquals(i.getTimeSlot(), 12);
        assertEquals(i.getCourse(), "cpsc");
    }

}
