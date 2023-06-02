package model.entity;

import model.Utility;

/**
 * Used as a base to represent a character within the dungeon game.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public abstract class DungeonCharacter implements Entity {

    //      FIELDS      //

    /** Character name. */
    private final String myName;

    /** Current health. */
    private int myHP;

    /** Maximum health. */
    private final int myMaxHP;

    /** Minimum damage. */
    private final int myMinDamage;

    /** Maximum damage. */
    private final int myMaxDamage;

    /** Attack speed. */
    private final int myAttackSpeed;

    /** Hit chance. */
    private final double myHitChance;


    //      CONSTRUCTORS        //

    /**
     * Create an instance of DungeonCharacter.
     *
     * @param theName Name.
     * @param theHP Hit points.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     */
    public DungeonCharacter(
            final String theName,
            final int    theHP,
            final int    theMinDamage,
            final int    theMaxDamage,
            final int    theAttackSpeed,
            final double theHitChance
    ) {

        myName = theName;
        myHP = theHP;
        myMaxHP = theHP;
        myMinDamage = theMinDamage;
        myMaxDamage = theMaxDamage;
        myAttackSpeed = theAttackSpeed;
        myHitChance = theHitChance;

    }


    //      ACCESSORS      //

    /**
     * Get character name.
     *
     * @return Name of character.
     */
    public final String getName() {
        return myName;
    }

    /**
     * Get current health.
     *
     * @return Current health.
     */
    public final int getHP() {
        return myHP;
    }

    /**
     * Get maximum health.
     *
     * @return Maximum health.
     */
    public final int getMaxHP() {
        return myMaxHP;
    }

    /**
     * Get minimum damage.
     *
     * @return Minimum damage.
     */
    public final int getMinDamage() {
        return myMinDamage;
    }

    /**
     * Get maximum damage.
     *
     * @return Maximum damage.
     */
    public final int getMaxDamage() {
        return myMaxDamage;
    }

    /**
     * Get attack speed.
     *
     * @return Attack speed.
     */
    public final int getAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     * Get hit chance.
     *
     * @return Hit chance.
     */
    public final double getHitChance() {
        return myHitChance;
    }


    //        MUTATORS        //

    /**
     * Attacks this character.
     *
     * @param theDamage Damage to deal to the character.
     * @throws IllegalArgumentException Negative damage is received.
     * @throws IndexOutOfBoundsException Character has died.
     */
    public void receiveDamage(final int theDamage) {

        // Prevent negative damage.
        if (theDamage < 0)
            throw new IllegalArgumentException(
                    "Cannot receive negative damage!"
            );

        // If lost too much health, the character has died. End the game here.
        if ((myHP = myHP - theDamage) <= 0) {
            myHP = 0;
            throw new IndexOutOfBoundsException(
                    "You died!"
            );
        }

    }

    /**
     * Adds health to this character.
     *
     * @param theHealth Health to give to the character.
     * @throws IllegalArgumentException Negative health is given.
     */
    public void receiveHealth(final int theHealth) {

        // Prevent negative health.
        if (theHealth < 0)
            throw new IllegalArgumentException(
                "Cannot receive negative health!"
            );

        // Set to max HP if overflowing, otherwise increment.
        // getMaxHP() call is necessary in the case we increase the max HP
        // later in the game.
        myHP = Math.min(myHP + theHealth, getMaxHP());

    }

    /**
     * Attacks another character.
     *
     * @param theCharacter Character to attack.
     */
    protected void attack(final DungeonCharacter theCharacter) {

        // Call parameter specific attack method.
        attack(theCharacter, myMinDamage, myMaxDamage);

    }

    /**
     * Attacks another character.
     *
     * @param theCharacter Character to attack.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     */
    protected void attack(
            final DungeonCharacter theCharacter,
            final int theMinDamage,
            final int theMaxDamage
    ) {

        // If out of the range, no damage.
        if (Utility.RANDOM.nextDouble() >= myHitChance) return;

        // Attack the other character.
        theCharacter.receiveDamage(
                Utility.RANDOM.nextInt(theMinDamage, theMaxDamage + 1)
        );

    }

}
