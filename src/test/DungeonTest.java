package test;

import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Door object. */
class DungeonTest {

    /** Dungeon to test this class with. */
    private Dungeon myDungeon;
    /** Hero to test if it is being stored correctly. */
    private Hero myHero;

    /** Create the dungeon to test with. */
    @BeforeEach
    void setUp() {
        myHero = new Warrior("dummy");
        myDungeon = new Dungeon(null, 5, 5, myHero);
    }

    /** Test if hero is set correctly. */
    @Test
    void getHero() {
        assertEquals(myHero, myDungeon.getHero());
    }

    /** Test if the isGamePlaying returns true. */
    @Test
    void isGamePlaying() {
        // Player shouldn't have entered the exit yet. so it should still be 'playing'
        assertTrue(myDungeon.isGamePlaying());
    }
}