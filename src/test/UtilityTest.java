package test;

import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveDungeonState() {
        Dungeon testDungeon = new Dungeon(null, 5, 5, new Priestess("test"));

        Utility.saveDungeonState(testDungeon, "testSave");

        Dungeon loaded = Utility.loadDungeonState(testDungeon, "testSave");

        assertEquals(testDungeon, loaded);
    }

//    @Test
//    void loadDungeonState() {
//    }

    @Test
    void generateHeroFromCharP() {
        Hero hero = Utility.generateHeroFromChar('P', "dummyHero");
        assertEquals('P', hero.getDisplayChar());
    }

    @Test
    void generateHeroFromCharW() {
        Hero hero = Utility.generateHeroFromChar('W', "dummyHero");
        assertEquals('W', hero.getDisplayChar());
    }

    @Test
    void generateHeroFromChaT() {
        Hero hero = Utility.generateHeroFromChar('T', "dummyHero");
        assertEquals('T', hero.getDisplayChar());
    }
}