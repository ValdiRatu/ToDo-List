package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Event;
import model.Item;
import model.ItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EventTest {
    private Item i;


    @BeforeEach
    void setUp() {
        i = new Event();
        i.setName("test");
        i.setDescription("test description");
        i.setTimeSlot(0);
    }

    @Test
    void testGetNSet() {
        i.setName("a");
        assertEquals("a", i.getName());
        i.setName("");
        assertEquals("", i.getName());
        assertEquals(ItemType.EVENT, i.getItemType());
        i.setDescription("abc");
        assertEquals("abc", i.getDescription());
        i.setTimeSlot(2);
        assertEquals(2, i.getTimeSlot());
        i.setDuration(4);
        assertEquals(i.getDuration(), 4);
        i.setCourse("shouldnt happen");
        assertEquals(i.getCourse(), "");
    }

    @Test
    void testConstructor2() {
        i = new Event("test2", "test", 12, 2);
        assertEquals(i.getName(), "test2");
        assertEquals(i.getDescription(), "test");
        assertEquals(i.getTimeSlot(), 12);
        assertEquals(i.getDuration(), 2);
    }

}
