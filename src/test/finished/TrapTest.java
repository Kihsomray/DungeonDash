package test.finished;

import model.entity.enemy.Trap;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.console.pattern.Color;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test Trap. */
class TrapTest {

    /** Trap to test with. */
    Trap myTrap;

    /** Setting up the Trap. */
    @BeforeEach
    void setUp() {
        myTrap = new Trap();
    }

    /** Test damageHero with a dummy hero. */
    @Test
    void damageHero() {
        Hero myHero = new Warrior("Dummy");
        int hp = myHero.getHP();

        myTrap.damageHero(myHero);

        assertTrue(hp >= myHero.getHP());
    }

    /** Test the display character. */
    @Test
    void getDisplayChar() {
        assertEquals('T', myTrap.getDisplayChar());
    }

    /** Test the colored display character. */
    @Test
    void getColoredDisplay() {
        assertEquals(Color.GREEN + 'T', myTrap.getColoredDisplay());
    }
}