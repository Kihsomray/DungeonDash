package test;

import model.Interactable;
import model.dungeon.cell.passable.Room;
import model.entity.enemy.Trap;
import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.Inventory;
import model.inventory.item.Item;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Testing the Room class. */
class RoomTest {

    Room myRoom;

    /** Setting up a Room. */
    @BeforeEach
    void setUp() {
        myRoom = new Room(1, 2);
    }

    @Test
    void testToString() {
        String outStr = "     \n" +
                "     ";
        Set<Interactable> set = myRoom.getInteractables();

        for (Interactable i : set) {
            if (i instanceof Monster) {
                outStr = "\u001B[31mM" + outStr;
            }
            if (i instanceof HealthPotion) {
                outStr = "\u001B[36mH " + outStr;
            } if (i instanceof VisionPotion) {
                outStr = "\u001B[36mV" + outStr;
            } if (i instanceof Trap) {
                outStr = "\u001B[32mT  " + outStr;
            }
        }

        outStr.replace(" ", ""); // TODO FIGURE THIS OUT

        assertEquals(outStr, myRoom.toString().replace(" ", ""));
    }

    @Test
    void interactWith() {
        Hero dHero = new Warrior("test");

        Inventory roomInv = myRoom.getInventory();
        myRoom.interactWith(dHero);

        for (Item i : roomInv.getInventory()) {
            assertTrue(dHero.getInventory().getInventory().contains(i));
        }
    }

    @Test
    void getX() {
        assertEquals(1, myRoom.getX());
    }

    @Test
    void getY() {
        assertEquals(2, myRoom.getY());
    }
}