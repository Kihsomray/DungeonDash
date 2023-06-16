package model.inventory.item;

import model.Interactable;

/**
 * An item is an object that can be stored within an inventory.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public interface Item extends Interactable {

    public String getSlotArtPath();

}
