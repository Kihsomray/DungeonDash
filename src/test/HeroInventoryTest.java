package test;

import model.inventory.HeroInventory;
import model.inventory.item.Item;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Test HeroInventory. */
class HeroInventoryTest {

    /** Create an Inventory to test with. */
    HeroInventory myInv;

    /** Set up HeroInventory. */
    @BeforeEach
    void setUp() {
        myInv = new HeroInventory();
    }

    /** Test to see if removing an item works. */
    @Test
    void removeItem() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        VisionPotion vP = new VisionPotion();
        myInv.addItem(vP);

        myInv.removeItem(hPotion);

        assertNull(myInv.getItemAt(0, 1, false));
        assertEquals(vP, myInv.getItemAt(1, 1, false));
    }

    /** Test to see if swapping an item works. */
    @Test
    void swapItemAt() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        VisionPotion vP = new VisionPotion();
        myInv.swapItemAt(0, 1, vP);

        assertEquals(vP, myInv.getItemAt(0, 1, false));
    }

    /** Test to see if adding an item works. */
    @Test
    void addItem() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        assertTrue(myInv.containsItem(hPotion));
    }

    /** Test to see if the height is correct. */
    @Test
    void getHeight() {
        assertEquals(3, myInv.getHeight());
    }

    /** Test to see if the width is correct. */
    @Test
    void getWidth() {
        assertEquals(3, myInv.getHeight());
    }

    /** Test if the getItemAt method works. */
    @Test
    void getItemAt() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        myInv.addItem(hPotion);
        assertEquals(hPotion, myInv.getItemAt(0, 1, false));

        assertEquals(hPotion, myInv.getItemAt(0, 1, true));

        assertNull(myInv.getItemAt(0, 1, false));
    }

    /** Test to see if getInventory works. */
    @Test
    void getInventory() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        assertTrue(myInv.getInventory().contains(hPotion));
    }

    /** Test to see if getSlots works. */
    @Test
    void getSlots() {
        HealthPotion hPotion = new HealthPotion();
        myInv.addItem(hPotion);

        Item[] inv = myInv.getSlots();
        assertEquals(hPotion, inv[4]);
    }

    /** Test to see if containsItem works. */
    @Test
    void containsItem() {
        HealthPotion hPotion = new HealthPotion();
        assertFalse(myInv.containsItem(hPotion));

        myInv.addItem(hPotion);
        assertTrue(myInv.containsItem(hPotion));
    }
}