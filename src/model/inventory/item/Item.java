package model.inventory.item;

import model.Entity;

/**
 * An item is an object that can be stored within an inventory.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public interface Item extends Entity {
    public String getSlotArtPath();
}
