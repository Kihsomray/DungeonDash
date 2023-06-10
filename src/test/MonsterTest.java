package test;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Monster class. */
class MonsterTest {

    /** Monster to test the class with. */
    private Monster myMonster;

    /** Set up the monster to test with. */
    @BeforeEach
    void setUp() {
        myMonster = new Monster("name", 100, 30, 30,
                3, 1, 40, 40, 1);
    }

    /** Test the receiveDamage method. */
    @Test
    void receiveDamage() {
        myMonster.receiveDamage(95);
        assertEquals(45, myMonster.getHP());
        assertEquals(55, myMonster.getLastDamage());
    }

    /** Test the attackHero method. */
    @Test
    void attackHero() {
        Hero myHero = new Warrior("test");
        int hp = myHero.getHP();
        myMonster.attackHero(myHero);

        assertEquals(hp - 30, myHero.getHP());
    }

    /** Simple getter test if the minHeal is correct. */
    @Test
    void getMinHeal() {
        assertEquals(40, myMonster.getMinHeal());
    }

    /** Simple getter test if the maxHealAmt is correct. */
    @Test
    void getMaxHealAmt() {
        assertEquals(40, myMonster.getMaxHealAmt());
    }

    /** Simple getter test if the healChance is correct. */
    @Test
    void getHealChance() {
        assertEquals(1.0, myMonster.getHealChance());
    }

    /** Simple getter test if the displayChar is correct. */
    @Test
    void getDisplayChar() {
        assertEquals('M', myMonster.getDisplayChar());
    }

    /** Simple getter test if the coloredDisplayChar is correct. */
    @Test
    void getColoredDisplay() {
        assertEquals("\u001B[31mM", myMonster.getColoredDisplay());
    }
}