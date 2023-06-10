package test;

import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import model.inventory.item.potion.VisionPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test VisionPotion. */
class VisionPotionTest {

    /** VisionPotion to test with. */
    VisionPotion myVisionPotion;

    /** Set up the VisionPotion. */
    @BeforeEach
    void setUp() {
        myVisionPotion = new VisionPotion();
    }

    /** Testing applying the VisionPotion to a dummy hero. */
    @Test
    void applyPotion() {
        Hero testHero = new Warrior("dummy");
        myVisionPotion.applyPotion(testHero);

        assertTrue(testHero.isExtraVisibility());
    }

    /** Test the display character. */
    @Test
    void getDisplayChar() {
        assertEquals('V', myVisionPotion.getDisplayChar());
    }
}