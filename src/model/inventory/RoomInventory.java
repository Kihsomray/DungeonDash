package model.inventory;

import model.inventory.item.Item;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A type of inventory that holds the contents of a dungeon room.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class RoomInventory implements Inventory, Serializable {

    /** Set of all items in the inventory.*/
    private final Set<Item> myItems;


    /**
     * Creates a new RoomInventory.
     */
    public RoomInventory() {

        // Set to empty hashset.
        myItems = new HashSet<>();

    }


    /**
     * Move all items from one this inventory to another.
     *
     * @param theInventory Inventory to transfer to.
     * @return String representation of the transfer.
     */
    public String addAllTo(final Inventory theInventory) {

        // Keep a loss and gain counter
        int loss = 0, gain = 0;
        for (final Item item : myItems) {
            try {
                theInventory.addItem(item);
                gain++;
            } catch (final IndexOutOfBoundsException theIOBE) {
                System.out.println(theIOBE.getMessage());
                loss++;
            }
        }

        myItems.removeAll(theInventory.getInventory());

        // Return a status string
        return loss > 0 ?

                // If inventory is full, return the lost amount.
                "Inventory is full!\nYou lost " + loss + " items!" :

                // Otherwise, tell them how much they gained.
                "Added " + gain + " items to your inventory!";

    }

    @Override
    public void addItem(final Item theItem) {
        myItems.add(theItem);
    }


    @Override
    public Set<Item> getInventory() {
        return new HashSet<>(myItems);
    }

    @Override
    public boolean containsItem(final Item theItem) {
        return myItems.contains(theItem);
    }

}
