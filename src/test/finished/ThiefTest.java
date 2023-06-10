package test.finished;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.entity.hero.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test Thief. */
class ThiefTest {

    /** Hero to test Thief with. */
    Hero myHero;

    /** Set up the hero beforehand. */
    @BeforeEach
    void setUp() {
        myHero = new Thief("Thief");
    }

    /** Testing the attackMonster method. */
    @Test
    void attackMonster() {
        Monster myMonster = new Monster("name", 100, 30, 30,
                3, 1, 40, 40, 0);
        myHero.attackMonster(myMonster, false);

        assertTrue(myMonster.getHP() <= 100);
    }

    /** Testing the attackMonster method but with a special attack. */
    @Test
    void attackMonsterSpecial() {
        Monster myMonster = new Monster("name", 100, 30, 30,
                3, 1, 40, 40, 1);
        myHero.attackMonster(myMonster, true);

        assertTrue(myMonster.getHP() <= 100);
    }

    /** Testing the display character. */
    @Test
    void getDisplayChar() {
        assertEquals('T', myHero.getDisplayChar());
    }
}