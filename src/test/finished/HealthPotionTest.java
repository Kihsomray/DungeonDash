package test.finished;

import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.item.potion.HealthPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {

    HealthPotion myHealthPotion;

    @BeforeEach
    void setUp() {
        myHealthPotion = new HealthPotion();
    }

    @Test
    void applyPotion() {
        Hero testHero = new Warrior("dummy");
        testHero.receiveDamage(50);

        int hpAfterDamage = testHero.getHP();
        myHealthPotion.applyPotion(testHero);

        assertTrue(testHero.getHP() > hpAfterDamage);
    }

    @Test
    void getDisplayChar() {
        assertEquals('H', myHealthPotion.getDisplayChar());
    }
}