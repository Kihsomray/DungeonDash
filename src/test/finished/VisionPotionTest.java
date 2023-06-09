package test.finished;

import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.item.potion.VisionPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisionPotionTest {

    VisionPotion myVisionPotion;

    @BeforeEach
    void setUp() {
        myVisionPotion = new VisionPotion();
    }

    @Test
    void applyPotion() {
        Hero testHero = new Warrior("dummy");
        myVisionPotion.applyPotion(testHero);

        assertTrue(testHero.isExtraVisibility());
    }

    @Test
    void getDisplayChar() {
        assertEquals('V', myVisionPotion.getDisplayChar());
    }
}