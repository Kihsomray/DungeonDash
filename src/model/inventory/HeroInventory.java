package model.inventory;

import com.sun.nio.sctp.IllegalUnbindException;
import model.util.Utility;
import model.inventory.item.collectable.Collectable;
import model.inventory.item.Item;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A type of inventory that stores all the hero's items.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class HeroInventory implements Inventory, Serializable {

    /** Inventory array of items. */
    private final Item[] myInventorySlots;

    /** Width of the inventory. */
    private final int myWidth;


    /**
     * Creates a new HeroInventory.
     */
    public HeroInventory() {
        this(3, 4);
    }

    /**
     * Creates a new HeroInventory with specific parameters.
     *
     * @param theHeight Height of inventory.
     * @param theWidth Width of inventory.
     */
    public HeroInventory(final int theHeight, final int theWidth) {

        // Size of the array.
        final int dimensions = theWidth * theHeight;

        // Check if it's too small.
        if (dimensions < 5) throw new IllegalArgumentException(
                "The inventory must have at least 5 slots!"
        );

        // Create the inventory.
        myInventorySlots = new Item[dimensions];
        myWidth = theWidth;
    }


    /**
     * Removes an item from the inventory, if present.
     *
     * @param theItem Item to remove.
     */
    public void removeItem(final Item theItem) {

        for (int i = 0; i < myInventorySlots.length; i++) {
            if (myInventorySlots[i] == theItem) {
                myInventorySlots[i] = null;
                return;
            }
        }

    }

    /**
     * Swaps an item at an X and Y coordinate within the inventory.
     *
     * X = 0, Y = 0 will be the item in position 0.
     *
     * @param theX X-coordinate.
     * @param theY Y-coordinate.
     * @return Item that was replaced.
     * @throws IndexOutOfBoundsException If values out of range.
     * @throws IllegalArgumentException If invalid collectable in reservation.
     */
    public Item swapItemAt(
            final int theX,
            final int theY,
            final Item theItem
    ) {

        final int index = theX + myWidth * theY;

        // Temporarily save the item.
        final Item item = myInventorySlots[index];

        // Check collectable slots.
        if (index < 4 && !(theItem instanceof Collectable))
            throw new IllegalArgumentException(
                    "A non-collectable cannot be placed in that spot!"
            );

        // Place in the item.
        myInventorySlots[index] = theItem;

        // Return the item.
        return item;

    }

    /**
     * Add an item to the inventory.
     *
     * @param theItem Item to add to inventory.
     * @throws IndexOutOfBoundsException Inventory is full.
     */
    @Override
    public void addItem(final Item theItem) {

        // Is the item a collectable?
        final boolean collectable = theItem instanceof Collectable;

        // Loop through collectables or standard items.
        for (int i = collectable ? 0 : 4;
             i < (collectable ? 4 : myInventorySlots.length); i++) {

            // If the slot is empty.
            if (myInventorySlots[i] == null) {

                // Set the item, and return.
                myInventorySlots[i] = theItem;
                return;

            }

        }

        // Otherwise, the inventory is deemed full.
        throw new IndexOutOfBoundsException("Hero inventory is full!");

    }


    /**
     * Get the height of the inventory.
     *
     * @return Height of the inventory.
     */
    public int getHeight() {
        return myInventorySlots.length / myWidth;
    }

    /**
     * Get the width of the inventory.
     *
     * @return Width of the inventory.
     */
    public int getWidth() {
        return myWidth;
    }


    /**
     * Gets an item at an X and Y coordinate within the inventory.
     *
     * X = 0, Y = 0 will be the item in position 0.
     *
     * @param theX X-coordinate.
     * @param theY Y-coordinate.
     * @return Item at that inventory location.
     * @throws IndexOutOfBoundsException If values out of range.
     */
    public Item getItemAt(
            final int theX,
            final int theY,
            final boolean theRemoveItem
    ) {

        // If remove the item.
        return theRemoveItem ?

                // Call the swap method with null.
                swapItemAt(theX, theY, null) :

                // Otherwise, return the item at the location.
                myInventorySlots[theX + myWidth * theY];

    }


    /**
     * Gets the inventory as a set.
     *
     * @return Inventory as a set.
     */
    @Override
    public Set<Item> getInventory() {
        return new HashSet<>(Arrays.stream(myInventorySlots).toList());
    }

    /**
     * Get a copy of the inventory as an array.
     *
     * @return Copy of slots in inventory.
     */
    public Item[] getSlots() {
        return Arrays.copyOf(myInventorySlots, myInventorySlots.length);
    }

    /**
     * Checks if the inventory contains an item.
     *
     * @param theItem Item to check.
     * @return If the inventory contains an item.
     */
    @Override
    public boolean containsItem(final Item theItem) {
        return Arrays.stream(myInventorySlots).anyMatch(e -> e == theItem);
    }


}
