package test;

import controller.DungeonAdventure;
import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Door object. */
class DungeonTest {

    private Dungeon myDungeon;

    /** Create the dungeon to test with. */
    @BeforeEach
    void setUp() {
        Hero dummyHero = new Warrior("dummy");
        myDungeon = new Dungeon(null, 5, 5, dummyHero);
    }

//    @Test
//    void getHero() {
//    }

//    @Test
//    void getMaze() {
//    }

//    @Test
//    void getRoomCount() {
//    }

//    @Test
//    void getMyWidth() {
//    }

//    @Test
//    void getMyHeight() {
//    }

//    @Test
//    void isGamePlaying() {
//    }
}