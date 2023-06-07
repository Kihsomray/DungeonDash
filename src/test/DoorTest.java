package test;

import model.entity.hero.Hero;
import model.entity.hero.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.dungeon.cell.passable.Door;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    Door myEntrance;
    Door myExit;

    @BeforeEach
    void setUp() {
        myEntrance = new Door(1, 1, true);
        myExit = new Door(2, 2, false);
    }

    @Test
    void getX() {
        assertEquals(1, myEntrance.getX());
    }

    @Test
    void getY() {
        assertEquals(1, myEntrance.getY());
    }

    @Test
    void testToString() {
        assertEquals("\u001B[33m<_^_>\n" +
                "\u001B[33m[]|[]", myEntrance.toString());
    }

    @Test
    void getNeighbors() {
        assertEquals("## ## ##\n" +
                "##    ##\n" +
                "## ## ##\n", myEntrance.getNeighbors().toString());
    }

    @Test
    void interactWith() {
        Hero dummyHero = new Thief("dummy");
        myEntrance.interactWith(dummyHero);
        assertFalse(myEntrance.hasEntered());
    }

    @Test
    void hasEntered() {
        assertFalse(myExit.hasEntered());
    }
}