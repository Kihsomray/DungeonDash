package test;

import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.item.potion.HealthPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test HealthPotions. */
class HealthPotionTest {

    /** Health potion to test with. */
    HealthPotion myHealthPotion;

    /** Create the health potion. */
    @BeforeEach
    void setUp() {
        myHealthPotion = new HealthPotion();
    }

    /** Test the applyPotion method on a dummy hero. */
    @Test
    void applyPotion() {
        Hero testHero = new Warrior("dummy");
        testHero.receiveDamage(50);

        int hpAfterDamage = testHero.getHP();
        myHealthPotion.applyPotion(testHero);

        assertTrue(testHero.getHP() > hpAfterDamage);
    }

    /** Test the getDisplayChar. */
    @Test
    void getDisplayChar() {
        assertEquals('H', myHealthPotion.getDisplayChar());
    }
}