package model.inventory.item.potion;

import model.Utility;
import model.entity.hero.Hero;
import model.inventory.item.Item;

import java.io.Serializable;

/**
 * A type of item that can give the hero a certain effect.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Potion implements Item, Serializable {


    /**
     * Creates a potion object.
     */
    public Potion() {

    }

    /**
     * Use a potion on the hero.
     *
     * @return Message from using the potion.
     */
    public String usePotion(final Hero theHero) {
        return "Not implemented";
    }

    /**
     * Gets the colored display of the hero.
     *
     * @return Colored display of the hero.
     */
    @Override
    public String getColoredDisplay() {
        return Utility.getColor('6') + getDisplayChar();
    }

}
