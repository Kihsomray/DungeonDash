package test;

import model.dungeon.cell.passable.Door;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.info.Neighbors;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NeighborsTest {

    Neighbors myNeighbors;

    /** Set up a Neighbors object and set up dummy Passable as neighbors. */
    @BeforeEach
    void setUp() {
        myNeighbors = new Neighbors(new Door(1, 1, false));

        Passable west = new Door(0, 0, false);
        Passable east = new Door(1, 1, false);
        Passable north = new Door(2, 2, false);
        Passable south = new Door(3, 3, false);

        myNeighbors.setEast(east, false);
        myNeighbors.setWest(west, false);
        myNeighbors.setNorth(north, false);
        myNeighbors.setSouth(south, false);
    }

    /** Tests getRandomNeighbor by creating a neighbors object and setting north, south, east, west to some dummy
     *  Passable and then check if when getting the random neighbors it acts like it should.
     */
    @org.junit.jupiter.api.Test
    void getRandomNeighbor() {

        Set<Passable> foundSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            Passable rand = myNeighbors.getRandomNeighbor(foundSet);
            //System.out.println(rand.getX());
            foundSet.add(rand);
        }

        assertEquals(4, foundSet.size());
    }

    /** Tests if the right neighbor is stored in the north position. */
    @org.junit.jupiter.api.Test
    void getNorth() {
        assertEquals(2, myNeighbors.getNorth().getX());
    }

    /** Tests if the right neighbor is stored in the east position. */
    @org.junit.jupiter.api.Test
    void getEast() {
        assertEquals(1, myNeighbors.getEast().getX());
    }

    /** Tests if the right neighbor is stored in the south position. */
    @org.junit.jupiter.api.Test
    void getSouth() {
        assertEquals(3, myNeighbors.getSouth().getX());
    }

    /** Tests if the right neighbor is stored in the west position. */
    @org.junit.jupiter.api.Test
    void getWest() {
        assertEquals(0, myNeighbors.getWest().getX());
    }

    /** Tests if the setting the neighbor works in the north position. */
    @org.junit.jupiter.api.Test
    void setNorth() {
        myNeighbors.setNorth(null, false);
        assertNull(myNeighbors.getNorth());
    }

    /** Tests if the setting the neighbor works in the east position. */
    @org.junit.jupiter.api.Test
    void setEast() {
        myNeighbors.setEast(null, false);
        assertNull(myNeighbors.getEast());
    }

    /** Tests if the setting the neighbor works in the south position. */
    @org.junit.jupiter.api.Test
    void setSouth() {
        myNeighbors.setSouth(null, false);
        assertNull(myNeighbors.getSouth());
    }

    /** Tests if the setting the neighbor works in the west position. */
    @org.junit.jupiter.api.Test
    void setWest() {
        myNeighbors.setWest(null, false);
        assertNull(myNeighbors.getWest());
    }
}