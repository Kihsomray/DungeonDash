package test.finished;

import model.inventory.RoomInventory;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test RoomInventory. */
class RoomInventoryTest {

    /** RoomInventory to test with. */
    RoomInventory myRoomInv;

    /** Setting up the RoomInventory. */
    @BeforeEach
    void setUp() {
        myRoomInv = new RoomInventory();
    }

    /** Testing the addAllTo method. */
    @Test
    void addAllTo() {
        RoomInventory newInv = new RoomInventory();

        HealthPotion hP = new HealthPotion();
        VisionPotion vP = new VisionPotion();
        myRoomInv.addItem(hP); myRoomInv.addItem(vP);

        myRoomInv.addAllTo(newInv);

        assertFalse(myRoomInv.containsItem(hP) || myRoomInv.containsItem(vP));
        assertTrue(newInv.containsItem(hP) && newInv.containsItem(vP));
    }

    /** Testing the addItem method. */
    @Test
    void addItem() {
        HealthPotion hP = new HealthPotion();
        myRoomInv.addItem(hP);
        assertTrue(myRoomInv.containsItem(hP));
    }

    /** Testing the getInventory method. */
    @Test
    void getInventory() {
        HealthPotion hP = new HealthPotion();
        myRoomInv.addItem(hP);
        assertTrue(myRoomInv.getInventory().contains(hP));
    }

    /** Testing the containsItem method. */
    @Test
    void containsItem() {
        HealthPotion hP = new HealthPotion();
        assertFalse(myRoomInv.containsItem(hP));
        myRoomInv.addItem(hP);
        assertTrue(myRoomInv.containsItem(hP));
    }
}