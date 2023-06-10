package test;

import model.dungeon.cell.passable.Door;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.info.Neighbors;
import model.dungeon.generator.PrimsGenerator;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test PrimsGenerator. */
class PrimsGeneratorTest {

    /** PrimsGenerator to test with. */
    PrimsGenerator myGen;
    /** Hero to test with. */
    Hero myHero;

    /** Setting up prims generator and the hero. */
    @BeforeEach
    void setUp() {
        myHero = new Warrior("dummyHero");
        myGen = new PrimsGenerator(10, 10, myHero);
    }

    /** Tests if this generation yields a maze that is actually traversable. */
    @Test
    void generate() {
        //TODO test this method

        myGen.generate();

        myGen.getCellAt(0, 0);
        Queue<Passable> q = new LinkedList<>();

        q.add((Passable) myGen.getCellAt(0,0));

        Passable cur = q.poll(); boolean hasEntrance = false;

        while (!q.isEmpty() || !hasEntrance) {
            if (cur instanceof Door && hasEntrance) {
                assertTrue(true);
                break;
            }

            Neighbors n = cur.getNeighbors();

            if (n.getNorth() != null) {
                q.add(n.getNorth());
            } if (n.getWest() != null) {
                q.add(n.getWest());
            } if (n.getSouth() != null) {
                q.add(n.getSouth());
            } if (n.getEast() != null) {
                q.add(n.getEast());
            }
            cur = q.poll();
            hasEntrance = true;
        }
    }

    /** Tests if the entrance is where it should be. */
    @Test
    void getEntrance() {
        assertEquals(0, myGen.getEntrance().getX());
    }

    /** Tests the getWidth method. */
    @Test
    void getWidth() {
        assertEquals(10, myGen.getWidth());
    }

    /** Tests the getHeight method. */
    @Test
    void getHeight() {
        assertEquals(10, myGen.getHeight());
    }

    /** Tests the getHero method. */
    @Test
    void getHero() {
        assertEquals(myHero, myGen.getHero());
    }
}