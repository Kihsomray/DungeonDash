package test.finished;

import model.inventory.item.collectable.Pillar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test the Pillar class. */
class PillarTest {

    /** Pillar to test with. */
    private Pillar myPillar;

    /** Set up a Pillar. */
    @BeforeEach
    void setUp() {
        myPillar = new Pillar(Pillar.Principle.ABSTRACTION);
    }

    /** Test if the correct display char is returned. */
    @Test
    void getDisplayChar() {
        assertEquals('A', myPillar.getDisplayChar());
    }

    /** Test if the correct colored display is returned. */
    @Test
    void getColoredDisplay() {
        assertEquals("[35mA", myPillar.getColoredDisplay());
    }
}