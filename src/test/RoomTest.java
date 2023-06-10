package test;

import model.Interactable;
import model.dungeon.cell.passable.Room;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.Inventory;
import model.inventory.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Testing the Room class. */
class RoomTest {

    /** Room to test the class with. */
    Room myRoom;

    /** Setting up a Room. */
    @BeforeEach
    void setUp() {
        myRoom = new Room(1, 2);
    }

    /** Testing the toString for the Room. */
    @Test
    void testToString() {
        Set<Interactable> set = myRoom.getInteractables();

        String toString = myRoom.toString();

        for (Interactable i : set) {
            assertTrue(toString.contains(i.getColoredDisplay()));
        }
    }

    /** Test if interactWith works correctly with a dummy hero. */
    @Test
    void interactWith() {
        Hero dHero = new Warrior("test");

        Inventory roomInv = myRoom.getInventory();
        myRoom.interactWith(dHero);

        for (Item i : roomInv.getInventory()) {
            assertTrue(dHero.getInventory().getInventory().contains(i));
        }
    }

    /** Test the getX method. */
    @Test
    void getX() {
        assertEquals(1, myRoom.getX());
    }

    /** Test the getY method. */
    @Test
    void getY() {
        assertEquals(2, myRoom.getY());
    }
}