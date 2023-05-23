package model.character.hero;

import model.Utility;
import model.character.DungeonCharacter;
import model.inventory.Inventory;
import model.character.monster.Monster;

/**
 * Used as a base to represent a hero within the dungeon game.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Hero extends DungeonCharacter {

    //        FIELDS        //

    /** Hero's inventory. */
    private final Inventory myInventory;

    /** Block chance. */
    private final double myBlockChance;

    /** Minimum heal */
    private final int myMinPotionHeal;

    /** Maximum heal */
    private final int myMaxPotionHeal;


    //        CONSTRUCTORS        //

    /**
     * Create an instance of Hero.
     *
     * @param theName Name.
     * @param theHP Hit points.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     * @param theBlockChance Block chance.
     * @param theMinPotionHeal Minimum potion heal.
     * @param theMaxPotionHeal Maximum potion heal.
     */
    public Hero(
            final String    theName,
            final int       theHP,
            final int       theMinDamage,
            final int       theMaxDamage,
            final int       theAttackSpeed,
            final double    theHitChance,
            final double    theBlockChance,
            final int       theMinPotionHeal,
            final int       theMaxPotionHeal
    ) {

        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
        );

        myInventory = new Inventory();
        myBlockChance = theBlockChance;
        myMinPotionHeal = theMinPotionHeal;
        myMaxPotionHeal = theMaxPotionHeal;

    }


    //        ACCESSORS        //

    /**
     * Get block chance.
     *
     * @return Block chance.
     */
    public final double getBlockChance() {
        return myBlockChance;
    }

    /**
     * Get the hero's inventory.
     *
     * @return Hero's inventory.
     */
    public final Inventory getInventory() {
        return myInventory;
    }

    /**
     * Get minimum heal.
     *
     * @return Minimum heal.
     */
    public int getMinPotionHeal() {
        return myMinPotionHeal;
    }

    /**
     * Get maximum heal.
     *
     * @return Maximum heal.
     */
    public int getMaxPotionHeal() {
        return myMaxPotionHeal;
    }


    //        MUTATORS        //

    /**
     * Attacks this hero.
     *
     * @param theDamage Damage to deal to the hero.
     */
    @Override
    public void receiveDamage(final int theDamage) {

        // If out of the range, no damage.
        if (Utility.RANDOM.nextDouble() >= myBlockChance) return;

        // Otherwise, call super.
        super.receiveDamage(theDamage);

    }

    /**
     * Attack a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use its ability.
     */
    public abstract void attackMonster(final Monster theMonster, final boolean theUseAbility);

    /**
     * Heal the hero based on min/max values.
     *
     * @return String status of the heal procedure.
     */
    public String healHero() {
        if (myInventory.hasPotion(true)) {

            // Amount to heal hero with potion.
            final int hpToHeal = Utility.RANDOM.nextInt(
                    myMinPotionHeal,
                    myMaxPotionHeal + 1
            );

            // Give the hero the necessary health.
            receiveHealth(hpToHeal);

            // Generate and return heal message.
            return generateHealMessage(hpToHeal);

        } else {

            // Otherwise, the user doesn't have any potions.
            return "You don't have any health potions.";

        }
    }

    /**
     * Generates heal message based on parameters.
     *
     * @param theHealAmount Amount healed.
     * @return String representation of the event.
     */
    private String generateHealMessage(final int theHealAmount) {

        // Using a StringBuilder.
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" was healed for ").append(theHealAmount);

        // Potential health wasted.
        final int healthWasted = theHealAmount + getHP() - getMaxHP();

        // Did the character lose any health?
        if (healthWasted > 0) {

            // Append the information.
            sb.append(", though they wasted ")
                    .append(healthWasted)
                    .append(" hit points");

        }

        // Final lines appended and returned.
        return sb.append(".\nCurrent HP is now: ").append(getHP()).toString();

    }
}
