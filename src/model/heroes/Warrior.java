package model.heroes;

import model.monsters.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Crushing Blow - 75 to 175 HP damage (40% success rate).
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Warrior extends Hero {

    //        FIELDS        //

    /** Ability minimum damage. */
    private static final int ABILITY_MIN_DAMAGE = 75;

    /** Ability maximum damage. */
    private static final int ABILITY_MAX_DAMAGE = 175;

    /** Ability chance. */
    private static final double ABILITY_CHANCE = 0.4;

    /**
     * Create an instance of a warrior.
     */
    public Warrior() {

        super(
                "Warrior",
                125,
                35,
                60,
                4,
                0.8,
                0.2,
                5,
                15
        );

    }


    //        MUTATORS        //

    /**
     * Attacks a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use the Crushing Blow ability.
     */
    public void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    ) {

        // Should use ability and 40% chance?
        if (theUseAbility && RANDOM.nextDouble() <= ABILITY_CHANCE) {

            // Attack the monster with the range of the ability.
            attack(theMonster, ABILITY_MIN_DAMAGE, ABILITY_MAX_DAMAGE);

        } else {

            // Otherwise, do a regular attack.
            attack(theMonster);

        }

    }

}
