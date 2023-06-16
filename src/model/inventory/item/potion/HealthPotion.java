package model.inventory.item.potion;

import model.util.Utility;
import model.entity.hero.Hero;

import java.io.File;

/**
 * A type of potion that restores a hero's health.
 *
 * Restores 6%-14% of the hero's maximum health.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patribird
 */
public final class HealthPotion extends Potion {

    /** Display character of vision potion */
    private static final char DISPLAY_CHAR = 'H';

    /** Minimum heal percentage */
    private static final double MIN_HEAL_PERCENTAGE = 0.12;

    /** Maximum heal percentage */
    private static final double MAX_HEAL_PERCENTAGE = 0.14;

    private static final String healthPotionArtPath = "res" + File.separator + "HealthPotion.png";
    private static final String healthSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "HealthSlot.png";

    //        CONSTRUCTORS        //

    /**
     * Creates a health potion object.
     */
    public HealthPotion() {

    }


    //        ACCESSORS        //

    @Override
    public char getDisplayChar() {
        return DISPLAY_CHAR;
    }
    private static final double MAX_HEAL_PERCENTAGE = 0.24;


    /**
     * Uses heal potion on the hero.
     */
    @Override
    public void applyPotion(final Hero theHero) {

        // Generate the amount to heal by.
        final int healAmount = (int) (theHero.getMaxHP() *
                Utility.RANDOM.nextDouble(
                        MIN_HEAL_PERCENTAGE,
                        MAX_HEAL_PERCENTAGE
                )
        );

        // Give the hero the necessary health.
        theHero.receiveHealth(healAmount);

    }


    @Override
    public char getDisplayChar() {
        return DISPLAY_CHAR;
    }

    public String getArtPath() {
        return healthPotionArtPath;
    }
    public String getSlotArtPath() {return healthSlotPath;}

}
