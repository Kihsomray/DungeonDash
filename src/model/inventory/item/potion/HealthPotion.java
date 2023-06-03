package model.inventory.item.potion;

import model.util.Utility;
import model.entity.hero.Hero;

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

    //        FIELDS        //

    /** Display character of vision potion */
    private static final char DISPLAY_CHAR = 'H';

    /** Minimum heal percentage */
    private static final double MIN_HEAL_PERCENTAGE = 0.06;

    /** Maximum heal percentage */
    private static final double MAX_HEAL_PERCENTAGE = 0.14;


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


    //        MUTATORS        //

    /**
     * Uses heal potion on the hero.
     */
    @Override
    public String usePotion(final Hero theHero) {

        // Generate the amount to heal by.
        final int healAmount = (int) (theHero.getMaxHP() *
                Utility.RANDOM.nextDouble(
                        MIN_HEAL_PERCENTAGE,
                        MAX_HEAL_PERCENTAGE
                )
        );

        // Give the hero the necessary health.
        theHero.receiveHealth(healAmount);

        // Generate heal message.
        return generateHealMessage(theHero, healAmount);

    }

    /**
     * Generates heal message based on parameters.
     *
     * @return String representation of the event.
     */
    private static String generateHealMessage(
            final Hero theHero,
            final int theHealAmount
    ) {

        // Using a StringBuilder.
        final StringBuilder sb = new StringBuilder(theHero.getName());
        sb.append(" was healed for ").append(theHealAmount);

        // Potential health wasted.
        final int healthWasted = theHealAmount +
                theHero.getHP() - theHero.getMaxHP();

        // Did the character lose any health?
        if (healthWasted > 0) {

            // Append the information.
            sb.append(", though they wasted ")
                    .append(healthWasted)
                    .append(" hit points");

        }

        // Final lines appended and returned.
        return sb.append(".\nCurrent HP is now: ")
                .append(theHero.getHP()).toString();

    }

}
