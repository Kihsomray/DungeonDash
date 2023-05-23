package model.inventory.potion;

import model.Utility;
import model.character.hero.Hero;

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

    /** Minimum heal percentage */
    private static final double MIN_HEAL_PERCENTAGE = 0.06;

    /** Maximum heal percentage */
    private static final double MAX_HEAL_PERCENTAGE = 0.14;

    /** Amount to heal the hero by */
    private final int myHealAmount;


    //        CONSTRUCTORS        //

    /**
     * Creates a health potion object binded to a hero.
     *
     * @param theHero Hero the potion is used on.
     */
    public HealthPotion(final Hero theHero) {

        super(theHero);

        // Generate the amount to heal by.
        myHealAmount = (int) (theHero.getMaxHP() *
                Utility.RANDOM.nextDouble(
                        MIN_HEAL_PERCENTAGE,
                        MAX_HEAL_PERCENTAGE
                )
        );

    }


    //        ACCESSORS        //

    /**
     * Gets heal amount.
     *
     * @return Heal amount.
     */
    public int getHealAmount() {
        return myHealAmount;
    }


    //        MUTATORS        //

    /**
     * Uses heal potion on the hero.
     */
    @Override
    public String usePotion() {

        // Give the hero the necessary health.
        myHero.receiveHealth(myHealAmount);

        // Generate heal message.
        return generateHealMessage();

    }

    /**
     * Generates heal message based on parameters.
     *
     * @return String representation of the event.
     */
    private String generateHealMessage() {

        // Using a StringBuilder.
        final StringBuilder sb = new StringBuilder(myHero.getName());
        sb.append(" was healed for ").append(myHealAmount);

        // Potential health wasted.
        final int healthWasted = myHealAmount +
                myHero.getHP() - myHero.getMaxHP();

        // Did the character lose any health?
        if (healthWasted > 0) {

            // Append the information.
            sb.append(", though they wasted ")
                    .append(healthWasted)
                    .append(" hit points");

        }

        // Final lines appended and returned.
        return sb.append(".\nCurrent HP is now: ")
                .append(myHero.getHP()).toString();

    }

}
