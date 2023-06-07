package test;

import model.dungeon.cell.passable.Door;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.Room;
import model.dungeon.cell.passable.info.Neighbors;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NeighborsTest {

    Neighbors myNeighbors;

    /** Set up a Neigbors object and set up dummy Passables as neighbors.
     *
     */
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
     *  Passables and then check if when getting the random neighbors it acts like it should.
     */
    @org.junit.jupiter.api.Test
    void getRandomNeighbor() {

        Set<Passable> foundSet = new HashSet<Passable>();
        for (int i = 0; i < 4; i++) {
            Passable rand = myNeighbors.getRandomNeighbor(foundSet);
            //System.out.println(rand.getX());
            foundSet.add(rand);
        }

        assertEquals(4, foundSet.size());
    }

    @org.junit.jupiter.api.Test
    void getNorth() {
        assertEquals(2, myNeighbors.getNorth().getX());
    }

    @org.junit.jupiter.api.Test
    void getEast() {
        assertEquals(1, myNeighbors.getEast().getX());
    }

    @org.junit.jupiter.api.Test
    void getSouth() {
        assertEquals(3, myNeighbors.getSouth().getX());
    }

    @org.junit.jupiter.api.Test
    void getWest() {
        assertEquals(0, myNeighbors.getWest().getX());
    }

    @org.junit.jupiter.api.Test
    void setNorth() {
        myNeighbors.setNorth(null, false);
        assertEquals(null, myNeighbors.getNorth());
    }

    @org.junit.jupiter.api.Test
    void setEast() {
        myNeighbors.setEast(null, false);
        assertEquals(null, myNeighbors.getEast());
    }

    @org.junit.jupiter.api.Test
    void setSouth() {
        myNeighbors.setSouth(null, false);
        assertEquals(null, myNeighbors.getSouth());
    }

    @org.junit.jupiter.api.Test
    void setWest() {
        myNeighbors.setWest(null, false);
        assertEquals(null, myNeighbors.getWest());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("##    ##\n        \n##    ##\n", myNeighbors.toString());
    }
}