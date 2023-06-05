package model.entity.enemy.monster;

import model.util.Utility;
import model.entity.DungeonCharacter;
import model.entity.enemy.Enemy;
import model.entity.hero.Hero;

import java.io.Serializable;

/**
 * Used as a base to represent a monster within the dungeon game.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class Monster extends DungeonCharacter implements Enemy, Serializable {

    //        FIELDS        //

    /** Minimum heal. */
    private final int myMinHeal;

    /** Maximum heal. */
    private final int myMaxHeal;

    /** Heal chance. */
    private final double myHealChance;


    //        CONSTRUCTORS        //

    /**
     * Create an instance of a monster based on MonsterData.
     *
     * @param theData MonsterData information.
     */
    public Monster(final MonsterData theData) {

        // Call the other constructor.
        this(
                theData.getName(),
                theData.getHP(),
                theData.getMinDamage(),
                theData.getMaxDamage(),
                theData.getAttackSpeed(),
                theData.getHitChance(),
                theData.getMinHeal(),
                theData.getMaxHeal(),
                theData.getHealChance()
        );

    }

    /**
     * Create an instance of Monster.
     *
     * @param theName Name.
     * @param theHP Hit points.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     * @param theMinHeal Minimum heal.
     * @param theMaxHeal Maximum heal.
     * @param theHealChance Heal chance.
     */
    public Monster(
            final String theName,
            final int theHP,
            final int theMinDamage,
            final int theMaxDamage,
            final int theAttackSpeed,
            final double theHitChance,
            final int theMinHeal,
            final int theMaxHeal,
            final double theHealChance
    ) {

        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
        );

        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
        myHealChance = theHealChance;

    }


    //        ACCESSORS        //

    /**
     * Get minimum heal.
     *
     * @return Minimum heal.
     */
    public int getMinHeal() {
        return myMinHeal;
    }

    /**
     * Get maximum heal.
     *
     * @return Maximum heal.
     */
    public int getMaxHealAmt() {
        return myMaxHeal;
    }

    /**
     * Get heal chance.
     *
     * @return Heal chance.
     */
    public double getHealChance() {
        return myHealChance;
    }

    /**
     * Gets the display char of the monster.
     *
     * @return Display char of the monster.
     */
    @Override
    public char getDisplayChar() {
        return 'M';
    }

    /**
     * Gets the colored display of the monster.
     *
     * @return Colored display of the monster.
     */
    @Override
    public String getColoredDisplay() {
        return Utility.getColor('1') + getDisplayChar();
    }


    //        MUTATORS        //

    /**
     * Attack this monster. Has a chance of healing itself after.
     *
     * @param theDamage Damage to deal to the monster.
     */
    @Override
    public void receiveDamage(final int theDamage) {
        super.receiveDamage(theDamage);

        // If no error is thrown, heal the monster.
        healMonster();
    }

    /**
     * Heal the monster after it has been attacked.
     */
    private void healMonster() {

        // If the chance is generated.
        if (Utility.RANDOM.nextDouble() < myHealChance) {

            // Give the monster some health back.
            receiveHealth(Utility.RANDOM.nextInt(myMinHeal, myMaxHeal + 1));

        }

    }

    /**
     * Attack a hero.
     *
     * @param theHero Hero to attack.
     */
    public void attackHero(final Hero theHero) {

        attack(theHero);

    }

}
