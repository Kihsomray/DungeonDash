package model.inventory.item.potion;

import model.entity.hero.Hero;
import model.inventory.item.Item;
import view.console.pattern.Color;

import java.io.Serializable;

/**
 * A type of item that can give the hero a certain effect.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Potion implements Item, Serializable {

    /**
     * Use a potion on the hero.
     *
     * @return Message from using the potion.
     */
    public abstract void applyPotion(final Hero theHero);


    /**
     * Gets the colored display of the hero.
     *
     * @return Colored display of the hero.
     */
    @Override
    public String getColoredDisplay() {
        return Color.CYAN + getDisplayChar();
    }

}
