package model.inventory.item.potion;

import model.sprite.hero.Hero;
import model.inventory.item.Item;

/**
 * A type of item that can give the hero a certain effect.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Potion implements Item {


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

}
