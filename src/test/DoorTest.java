package test;

import model.entity.hero.Hero;
import model.entity.hero.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.dungeon.cell.passable.Door;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Door object. */
class DoorTest {

    /** Door entrance to test the Door. */
    Door myEntrance;
    /** Door exit to test the Door. */
    Door myExit;

    /** Create the doors to test with. */
    @BeforeEach
    void setUp() {
        myEntrance = new Door(1, 1, true);
        myExit = new Door(2, 2, false);
    }

    /** Simple test for a getter. */
    @Test
    void getX() {
        assertEquals(1, myEntrance.getX());
    }

    /** Simple test for a getter. */
    @Test
    void getY() {
        assertEquals(1, myEntrance.getY());
    }

    /** Testing the toString method, color included. */
    @Test
    void testToString() {
        assertEquals("\u001B[33m<_^_>\n" +
                "\u001B[33m[]|[]", myEntrance.toString());
    }

//    @Test
//    void getNeighbors() {
        // No need to test simple getter.
//    }

    /** Test interactWith, see if a hero can interact with a door. */
    @Test
    void interactWith() {
        Hero dummyHero = new Thief("dummy");
        myEntrance.interactWith(dummyHero);
        assertFalse(myEntrance.hasEntered());
    }

    /** Simple test for a boolean. */
    @Test
    void hasEntered() {
        assertFalse(myExit.hasEntered());
    }
}