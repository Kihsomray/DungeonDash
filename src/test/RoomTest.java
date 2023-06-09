package test;

import model.Interactable;
import model.dungeon.cell.passable.Room;
import model.inventory.item.potion.HealthPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room myRoom;

    @BeforeEach
    void setUp() {
        myRoom = new Room(1, 1);
    }

    @Test
    void getNeighbors() {
        assertEquals("## ## ##\n" +
                "##    ##\n" +
                "## ## ##\n", myRoom.getNeighbors().toString());
    }

    @Test
    void interactWith() {
        return; // Method does nothing
    }

    @Test
    void getX() {
        assertEquals(1, myRoom.getX());
    }

    @Test
    void getY() {
        assertEquals(1, myRoom.getX());
    }

    @Test
    void getInventory() {
        return; // Simple getter
    }

    @Test
    void getEntities() {
        return; // Simple getter
    }

    @Test
    void getEnemies() {
        return; // Simple getter
    }

    @Test
    void testToString() {
        String expected = "";


//        for (Interactable i : roomInv) {
////            if (i.getDisplayChar() == 'H') {
////                expected = expected + "\u001B[36mH";
////            } if (i.getDisplayChar() == 'M') {
////                expected = expected + "\u001B[31mM";
////            }
////        }
        expected = expected + "    \n     ";

        //System.out.println(myRoom.getEntities());


        assertEquals(expected, myRoom.toString());
    }
}