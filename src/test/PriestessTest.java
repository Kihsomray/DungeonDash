package test;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Priestess;
import model.entity.hero.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test Priestess. */
class PriestessTest {

    /** Hero to test Priestess with. */
    Hero myHero;

    /** Set up the hero beforehand. */
    @BeforeEach
    void setUp() {
        myHero = new Priestess("Priestess");
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
        myMonster.attackHero(myHero);
        int hp = myHero.getHP();
        myHero.attackMonster(myMonster, true);

        assertTrue(myHero.getHP() > hp);
    }

    /** Testing the display character. */
    @Test
    void getDisplayChar() {
        assertEquals('P', myHero.getDisplayChar());
    }
}