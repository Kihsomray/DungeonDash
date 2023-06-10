package test;

import model.dungeon.cell.Wall;
import model.dungeon.cell.passable.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Wall object. */
class WallTest { // TODO find if wall needs testing to begin with

    /** Wall, used to test. */
    Wall myWall;

    /** Initialize a wall to test. */
    @BeforeEach
    void setUp() {
        myWall = new Wall(1, 1);
    }

    /** Simple test for a getter. */
    @Test
    void getX() {
        assertEquals(1, myWall.getX());
    }

    /** Simple test for a getter. */
    @Test
    void getY() {
        assertEquals(1, myWall.getY());
    }
}